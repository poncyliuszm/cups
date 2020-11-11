import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {CupService} from "../../services/cup.service";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatPaginator} from "@angular/material/paginator";
import {merge, of as observableOf} from "rxjs";
import {catchError, map, startWith, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-cups',
  templateUrl: './cup-list.component.html',
  styleUrls: ['./cup-list.component.css']
})
export class CupListComponent implements OnInit, AfterViewInit {

  cups: [];
  cupsDataSource = new MatTableDataSource();
  displayedColumns: string[] = ['select', 'name', 'category', 'description', 'actions'];
  selection = new SelectionModel<any>(true, []);

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private cupService: CupService,
              private router: Router,
              private matDialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.cups = [];
  }

  ngAfterViewInit() {
    this.getCupList();
  }

  getCupList() {
    merge(this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.cupService.findCups(this.paginator.pageIndex, this.paginator.pageSize)
        }),
        map(data => {
          this.isLoadingResults = false;
          this.resultsLength = data['totalElements'];

          return data;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.cups = data['content']);
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.cups.forEach(row => this.selection.select(row));
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.cups.length;
    return numSelected === numRows;
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
    let dialogRef = this.matDialog.open(DeleteCupDialog, {});

    dialogRef.afterClosed().subscribe(result => {
      if (result === true)
        this.deleteCup(cup);
      else
        return;
    });
  }

  deleteCup(cup: any) {
    this.cupService.deleteCup(cup)
      .subscribe(() => {
        this.snackBar.open('Pucharek został usunięty.', 'Zamknij', {duration: 5000});
        this.getCupList();
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
export class DeleteCupDialog {

  constructor(public dialogRef: MatDialogRef<DeleteCupDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
