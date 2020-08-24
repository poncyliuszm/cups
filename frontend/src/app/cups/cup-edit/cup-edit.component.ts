import {Component, OnInit} from '@angular/core';
import {CupService} from "../../services/cup.service";
import {CategoryService} from "../../services/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {UtilsComponent} from "../../utils/utils.component";

@Component({
  selector: 'app-cup-add',
  templateUrl: './cup-edit.component.html',
  styleUrls: ['./cup-edit.component.css']
})
export class CupEditComponent implements OnInit {
  cupId: any;
  categoryId: any;
  cupName: any;
  categories: any;

  cup = {
    cupId: "",
    name: "",
    category: {
      "id": "",
      "name": ""
    },
    description: "",
    solution: ""
  };

  constructor(private cupService: CupService,
              private categoryService: CategoryService,
              public utilsComponent: UtilsComponent,
              private router: Router,
              private snackBar: MatSnackBar,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.cupId = +params['id'];
    });
    this.getCup();
    this.getCategories();
  }

  getLastNameOfCategory(event: any) {
    let category = event.source.value;
    if (this.categoryId != category.id) {
      this.cupService.getLastNameOfCategory(category.id)
        .subscribe((data: any) => {
          this.cup.name = data;
        })
    } else {
      this.cup.name = this.cupName;
    }
  }

  save() {
    this.cup['categoryId'] = this.cup.category.id;
    delete this.cup.category;
    this.cupService.editCup(this.cup)
      .subscribe(() => {
        this.snackBar.open('Pucharek został zapisany.', 'Zamknij', {duration: 5000});
        this.router.navigate(['cups']);
      })
  }

  private getCup() {
    this.cupService.getCup(this.cupId)
      .subscribe((cup: any) => {
        this.cup = cup;
        this.categoryId = this.cup.category.id;
        this.cupName = this.cup.name;
      })
  }

  private getCategories() {
    this.categoryService.list()
      .subscribe((data: any) => {
        this.categories = data;
      })
  }

}
