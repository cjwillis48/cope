import { Component, Input, OnInit, ViewContainerRef } from '@angular/core';
import { NzModalRef, NzModalService } from "ng-zorro-antd/modal";
import * as _ from 'lodash';

@Component({
  selector: 'app-json-object-viewing',
  templateUrl: './object-viewer-modal.component.html',
  styleUrls: ['./object-viewer-modal.component.css']
})
export class ObjectViewerModal implements OnInit {
  @Input() subtitle?: string;
  @Input() data?: any;

  constructor(private modal: NzModalRef, private modalService: NzModalService, private viewContainerRef: ViewContainerRef) {}

  ngOnInit(): void {
  }

  getKeys() {
    return Object.keys(this.data);
  }

  isJson(value) {
    return value && typeof value === 'object';
  }

  keyToTitle(key: string) {
    return _.startCase(key);
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


  close() {
    this.modal.destroy({})
  }

}
