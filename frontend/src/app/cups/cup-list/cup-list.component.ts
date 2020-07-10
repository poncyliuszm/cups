import {Component, Inject, OnInit} from '@angular/core';
import {CupService} from "../../services/cup.service";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-cups',
  templateUrl: './cup-list.component.html',
  styleUrls: ['./cup-list.component.css']
})
export class CupListComponent implements OnInit {

  cups: [];
  cupsDataSource = new MatTableDataSource();
  displayedColumns: string[] = ['select', 'lp', 'name', 'category', 'description', 'actions'];
  selection = new SelectionModel<any>(true, []);

  constructor(private cupService: CupService,
              private router: Router,
              private matDialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.getCupList();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.cupsDataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.cupsDataSource.data.forEach(row => this.selection.select(row));
  }

  checkboxLabel(row?: any): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  preview(cup: any) {
    this.router.navigate(['cups/preview/', cup.id]);
  }

  editCup(cup: any) {
    this.router.navigate(['cups/edit/', cup.id])
  }

  openDeleteDialog(cup: any) {
    let dialogRef = this.matDialog.open(DeleteUserDialog, {});

    dialogRef.afterClosed().subscribe(result => {
      if (result === true)
        this.deleteUser(cup);
      else
        return;
    });
  }

  deleteUser(cup: any) {
    this.cupService.deleteCup(cup)
      .subscribe(() => {
        this.snackBar.open('Pucharek został usunięty.', 'Zamknij', {duration: 5000});
        this.getCupList();
      })
  }

  private getCupList() {
    this.cupService.list()
      .subscribe((cups: any) => {
        this.cups = cups;
        this.cupsDataSource = new MatTableDataSource<any>(cups);
      })
  }
}

@Component({
  selector: 'delete-dialog',
  template: "<h3 mat-dialog-title style='text-align: center'>Usunięcie pucharka</h3>\n" +
    "<mat-dialog-content >  Czy na pewno chcesz usunąć ten pucharek?</mat-dialog-content>\n" +
    "<mat-dialog-actions style='justify-content: center'>\n" +
    "  <button mat-button [mat-dialog-close]=\"true\" tabindex=\"1\">Tak</button>\n" +
    "  <button mat-button [mat-dialog-close]=\"false\" tabindex=\"-1\">Anuluj</button>\n" +
    "</mat-dialog-actions>"
})
export class DeleteUserDialog {

  constructor(public dialogRef: MatDialogRef<DeleteUserDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
