import {Component, Inject, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  categories: [];
  categoriesDataSource = new MatTableDataSource();
  displayedColumns: string[] = ['select', 'lp', 'name', 'actions'];
  selection = new SelectionModel<any>(true, []);

  constructor(private categoryService: CategoryService,
              private router: Router,
              private matDialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.getCategoryList();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.categoriesDataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.categoriesDataSource.data.forEach(row => this.selection.select(row));
  }

  checkboxLabel(row?: any): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  preview(category: any) {
    this.router.navigate(['categories/preview/', category.id]);
  }

  editCategory(category: any) {
    this.router.navigate(['categories/edit/', category.id])
  }

  openDeleteDialog(category: any) {
    let dialogRef = this.matDialog.open(DeleteCategoryDialog, {});

    dialogRef.afterClosed().subscribe(result => {
      if (result === true)
        this.deleteCategory(category);
      else
        return;
    });
  }

  deleteCategory(cup: any) {
    this.categoryService.deleteCup(cup.id)
      .subscribe(() => {
        this.snackBar.open('Kategoria została usunięta.', 'Zamknij', {duration: 5000});
        this.getCategoryList();
      })
  }

  private getCategoryList() {
    this.categoryService.list()
      .subscribe((categories: any) => {
        this.categories = categories;
        this.categoriesDataSource = new MatTableDataSource<any>(categories);
      })
  }
}

@Component({
  selector: 'delete-dialog',
  template: "<h3 mat-dialog-title style='text-align: center'>Usunięcie kategorii</h3>\n" +
    "<mat-dialog-content >  Czy na pewno chcesz usunąć tą kategorię?</mat-dialog-content>\n" +
    "<mat-dialog-actions style='justify-content: center'>\n" +
    "  <button mat-button [mat-dialog-close]=\"true\" tabindex=\"1\">Tak</button>\n" +
    "  <button mat-button [mat-dialog-close]=\"false\" tabindex=\"-1\">Anuluj</button>\n" +
    "</mat-dialog-actions>"
})
export class DeleteCategoryDialog {

  constructor(public dialogRef: MatDialogRef<DeleteCategoryDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
