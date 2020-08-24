import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent implements OnInit {

  categories: any;
  category = {
    name: "",
    description: "",
    parent: {}
  };

  constructor(private categoryService: CategoryService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.getCategories();
  }

  save() {
    this.categoryService.addCategory(this.category)
      .subscribe(() => {
        this.snackBar.open('Kategoria została dodana.', 'Zamknij', {duration: 5000});
        this.router.navigate(['categories']);
      })
  }

  private getCategories() {
    this.categoryService.list()
      .subscribe((data: any) => {
        this.categories = data;
      })
  }

}


