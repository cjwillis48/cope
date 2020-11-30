import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ColumnItem} from "../../../shared/interfaces/column-item.interface";
import { Acknowledgement, Parameter } from "../../interfaces/acknowledgement.interface";
import {AcknowledgementService} from "../../services/acknowledgement.service";
import {NzModalService} from "ng-zorro-antd/modal";
import {ObjectViewerModal} from "../../../shared/components/object-viewer-modal/object-viewer-modal.component";
import { Client } from "../../../shared/interfaces/client.interface";
import { ParameterViewerComponent } from "../parameter-viewer/parameter-viewer.component";

@Component({
  selector: 'app-complications',
  templateUrl: './view-acknowledgements.component.html',
  styleUrls: ['./view-acknowledgements.component.css']
})
export class ViewAcknowledgementsComponent implements OnInit {
  loaded: boolean = false;

  columns: ColumnItem[] = [
    {
      title: 'UUID',
      compare: (a: Acknowledgement, b: Acknowledgement) => a.uuid.localeCompare(b.uuid),
      priority: 3
    },
    {
      title: 'Operation',
      compare: (a: Acknowledgement, b: Acknowledgement) => a.operation.localeCompare(b.operation),
      priority: 4
    },
    {
      title: 'Success',
      compare: (a: Acknowledgement, b: Acknowledgement) => a.success ? -1 : (b.success ? 1 : -1),
      priority: 5
    },
    {
      title: 'Input',
      compare: (a: Acknowledgement, b: Acknowledgement) => 0,
      priority: 100
    },
    {
      title: 'Output',
      compare: (a: Acknowledgement, b: Acknowledgement) => 0,
      priority: 101
    },
    {
      title: 'Client',
      compare: (a: Acknowledgement, b: Acknowledgement) => 0,
      priority: 102
    },
    {
      title: 'Start Time',
      compare: (a: Acknowledgement, b: Acknowledgement) => new Date(a.startTime).getTime() - new Date(b.startTime).getTime(),
      priority: 2
    },
    {
      title: 'Complete Time',
      compare: (a: Acknowledgement, b: Acknowledgement) => new Date(a.completeTime).getTime() - new Date(b.completeTime).getTime(),
      priority: 1
    },
  ];

  data: Acknowledgement[];

  constructor(
    private acknowledgementService: AcknowledgementService,
    private modalService: NzModalService,
    private viewContainerRef: ViewContainerRef) {
  }

  ngOnInit(): void {
    this.acknowledgementService.getAcknowledgements().subscribe(res => {
      this.loaded = true;
      this.data = res;
    });
  }

  showOutputModal(output: string) {
    this.modalService.create({
      nzTitle: 'Output',
      nzContent: ObjectViewerModal,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        data: JSON.parse(output)
      }
    });
  }

  showInputModal(input: Parameter[]) {
    this.modalService.create( {
      nzTitle: 'Input',
      nzContent: ParameterViewerComponent,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        data: input
      }
    })
  }

  showClientModal(client: Client) {
    this.modalService.create({
      nzTitle: 'Client',
      nzContent: ObjectViewerModal,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        data: client
      }
    });
  }

}
