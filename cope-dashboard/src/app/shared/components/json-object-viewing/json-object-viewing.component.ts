import {Component, Input, OnInit} from '@angular/core';
import {NzModalRef} from "ng-zorro-antd/modal";

@Component({
  selector: 'app-json-object-viewing',
  templateUrl: './json-object-viewing.component.html',
  styleUrls: ['./json-object-viewing.component.css']
})
export class JsonObjectViewingComponent implements OnInit {
  @Input() title?: string;
  @Input() subtitle?: string;
  @Input() data?: string;

  constructor(private modal: NzModalRef) {}

  ngOnInit(): void {
  }

  close() {
    this.modal.destroy({})
  }

}
