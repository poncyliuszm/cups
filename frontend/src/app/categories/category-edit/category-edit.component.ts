import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {UtilsComponent} from "../../utils/utils.component";

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.css']
})
export class CategoryEditComponent implements OnInit {

  categories: any;
  categoryId: any;
  category = {
    name: "",
    description: "",
    parent: {}
  };

  constructor(private categoryService: CategoryService,
              public utilsComponent: UtilsComponent,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.categoryId = +params['id'];
    });
    this.getCategory();
    this.getCategories();
  }

  save() {
    this.categoryService.editCategory(this.category)
      .subscribe(() => {
        this.snackBar.open('Kategoria została zapisana.', 'Zamknij', {duration: 5000});
        this.router.navigate(['categories']);
      })
  }

  private getCategory() {
    this.categoryService.getCategory(this.categoryId)
      .subscribe((category: any) => {
        this.category = category;
      })
  }

  private getCategories() {
    this.categoryService.list()
      .subscribe((data: any) => {
        this.categories = data;
      })
  }

}
