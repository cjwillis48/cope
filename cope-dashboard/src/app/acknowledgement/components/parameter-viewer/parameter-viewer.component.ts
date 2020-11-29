import { Component, Input, OnInit, ViewContainerRef } from '@angular/core';
import { Parameter } from "../../interfaces/acknowledgement.interface";
import { NzModalRef, NzModalService } from "ng-zorro-antd/modal";
import * as _ from 'lodash';
import { ObjectViewerModal } from "../../../shared/components/object-viewer-modal/object-viewer-modal.component";

@Component({
  selector: 'app-parameter-viewer',
  templateUrl: './parameter-viewer.component.html',
  styleUrls: ['./parameter-viewer.component.css']
})
export class ParameterViewerComponent implements OnInit {

  @Input() data?: Parameter[];

  constructor(private modal: NzModalRef, private modalService: NzModalService, private viewContainerRef: ViewContainerRef) {}


  ngOnInit(): void {
    this.data = this.data.map(p => {
      try {
        p.value = JSON.parse(p.value);
      } catch (e) {

      }
      return p;
    });
    console.log('test');
  }

  keyToTitle(key: string) {
    return _.startCase(key);
  }

  isJson(value) {
    return value && typeof value === 'object';
  }

  showModal(key, data) {
    this.modalService.create({
      nzTitle: this.keyToTitle(key),
      nzContent: ObjectViewerModal,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        data: data
      }
    });
  }

}
