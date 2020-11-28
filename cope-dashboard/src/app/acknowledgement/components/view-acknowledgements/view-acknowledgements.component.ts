import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ColumnItem} from "../../../shared/interfaces/column-item.interface";
import {Acknowledgement} from "../../interfaces/acknowledgement.interface";
import {AcknowledgementService} from "../../services/acknowledgement.service";
import {NzModalService} from "ng-zorro-antd/modal";
import {JsonObjectViewingComponent} from "../../../shared/components/json-object-viewing/json-object-viewing.component";

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
      compare: (a: Acknowledgement, b: Acknowledgement) => a.startTime.getTime() - b.startTime.getTime(),
      priority: 2
    },
    {
      title: 'Complete Time',
      compare: (a: Acknowledgement, b: Acknowledgement) => a.completeTime.getTime() - b.completeTime.getTime(),
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
      console.log(res);
    })
  }

  showOutputModal(output: string) {
    const modal = this.modalService.create({
      nzTitle: 'Output',
      nzContent: JsonObjectViewingComponent,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        title: 'title in component',
        subtitle: 'component sub title，will be changed after 2 sec',
        data: output
      },
      nzOnOk: () => new Promise(resolve => setTimeout(resolve, 1000)),
    });
    // const instance = modal.getContentComponent();
    // modal.afterOpen.subscribe(() => console.log('[afterOpen] emitted!'));
    // // Return a result when closed
    // modal.afterClose.subscribe(result => console.log('[afterClose] The result is:', result));
    //
    // // delay until modal instance created
    // setTimeout(() => {
    //   instance.subtitle = 'sub title is changed';
    // }, 2000);
  }

}
