import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {STEPPER_GLOBAL_OPTIONS} from "@angular/cdk/stepper";
import {CupService} from "../services/cup.service";
import {RepeatService} from "../services/repeat.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-repeat',
  templateUrl: './repeat.component.html',
  styleUrls: ['./repeat.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }]
})
export class RepeatComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  fourthFormGroup: FormGroup;
  fifthFormGroup: FormGroup;

  repeatCups = [];
  answers = [];

  // repeatCups = [{}, {}, {}, {}, {}];

  constructor(private formBuilder: FormBuilder,
              private cupService: CupService,
              private repeatService: RepeatService,
              private snackBar: MatSnackBar,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getRepeatCups();

    this.firstFormGroup = this.formBuilder.group({
      answer: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
      answer: ['', Validators.required]
    });
    this.thirdFormGroup = this.formBuilder.group({
      answer: ['', Validators.required]
    });
    this.fourthFormGroup = this.formBuilder.group({
      answer: ['', Validators.required]
    });
    this.fifthFormGroup = this.formBuilder.group({
      answer: ['', Validators.required]
    });

  }

  private getRepeatCups() {
    this.cupService.getRepeatCups()
      .subscribe((data: any) => {
        this.repeatCups = data;
      })
  }

  sendAnswers() {
    let an1 = {
      "id": this.repeatCups[0].id,
      "answer": this.firstFormGroup.value.answer
    };

    let an2 = {
      "id": this.repeatCups[1].id,
      "answer": this.secondFormGroup.value.answer
    };

    let an3 = {
      "id": this.repeatCups[2].id,
      "answer": this.thirdFormGroup.value.answer
    };

    let an4 = {
      "id": this.repeatCups[3].id,
      "answer": this.fourthFormGroup.value.answer
    };

    let an5 = {
      "id": this.repeatCups[4].id,
      "answer": this.fifthFormGroup.value.answer
    };


    this.answers.push(an1, an2, an3, an4, an5);
    this.repeatService.sendAnswers(this.answers)
      .subscribe((data: any) => {
        this.snackBar.open('Odpowiedzi wysłane.', 'Zamknij', {duration: 5000});
        // this.router.navigate(['cups']);
      })
  }
}
