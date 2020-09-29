import {Component, OnInit} from '@angular/core';
import {CupService} from "../../services/cup.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-cup-add',
  templateUrl: './cup-preview.component.html',
  styleUrls: ['./cup-preview.component.css']
})
export class CupPreviewComponent implements OnInit {
  cupId: any;
  category: any;
  name: any;

  cup = {
    name: "",
    category: {
      "id": "",
      "name": ""
    },
    description: "",
    solution: ""
  };

  constructor(private cupService: CupService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.cupId = +params['id'];
    });
    this.getCup();
  }


  private getCup() {
    this.cupService.getCup(this.cupId)
      .subscribe((cup: any) => {
        this.cup = cup;
      })
  }
}
