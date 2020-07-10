import {Component, OnInit} from '@angular/core';
import {CupService} from "../../services/cup.service";
import {CategoryService} from "../../services/category.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cup-add',
  templateUrl: './cup-add.component.html',
  styleUrls: ['./cup-add.component.css']
})
export class CupAddComponent implements OnInit {
  categories: any;
  category: any;
  name: any;

  cup = {
    name: "",
    categoryId: "",
    description: "",
    solution: ""
  };

  constructor(private cupService: CupService,
              private categoryService: CategoryService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.getCategories();
  }

  getLastNameOfCategory(event: any) {
    this.cupService.getLastNameOfCategory(event.value)
      .subscribe((data: any) => {
        this.cup.name = data;
      })

  }

  save() {
    this.cupService.addCup(this.cup)
      .subscribe(() => {
        this.snackBar.open('Pucharek został dodany.', 'Zamknij', {duration: 5000});
        this.router.navigate(['cups']);
      })
  }

  private getCategories() {
    this.categoryService.list()
      .subscribe((data: any) => {
        this.categories = data;
      })
  }
}
