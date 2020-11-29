import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ColumnItem } from "../../../shared/interfaces/column-item.interface";
import { Patient, Prescriber, Prescription } from "../../interfaces/prescription.interface";
import { PrescriptionService } from "../../services/prescription.service";
import { NzModalService } from "ng-zorro-antd/modal";
import { Client } from "../../../shared/interfaces/client.interface";
import { Acknowledgement } from "../../../acknowledgement/interfaces/acknowledgement.interface";
import { ObjectViewerModal } from "../../../shared/components/object-viewer-modal/object-viewer-modal.component";

@Component({
  selector: 'app-view-prescriptions',
  templateUrl: './view-prescriptions.component.html',
  styleUrls: ['./view-prescriptions.component.css']
})
export class ViewPrescriptionsComponent implements OnInit {
  loaded: boolean = false;
  data: Prescription[];
  columns: ColumnItem[] = [
    {
      title: 'Patient',
      compare: (a: Prescription, b: Prescription) => (`${a.patient.lastName}, ${a.patient.firstName}`).localeCompare(`${b.patient.lastName}, ${b.patient.firstName}`),
      priority: 2
    },
    {
      title: 'Prescriber',
      compare: (a: Prescription, b: Prescription) => a.prescriber.lastName.localeCompare(b.prescriber.lastName),
      priority: 3
    },
    {
      title: 'Pharmacy',
      compare: (a: Prescription, b: Prescription) => a.pharmacy.name.localeCompare(b.pharmacy.name),
      priority: 8
    },
    {
      title: 'Drug Name',
      compare: (a: Prescription, b: Prescription) => a.drugName.localeCompare(b.drugName),
      priority: 5
    },
    {
      title: 'Quantity',
      compare: (a: Prescription, b: Prescription) => a.quantity - b.quantity,
      priority: 6
    },
    {
      title: 'Dose',
      compare: (a: Prescription, b: Prescription) => (`${a.dose} ${a.dosageMeasurement}`).localeCompare(`${b.dose} ${b.dosageMeasurement}`),
      priority: 7
    },
    {
      title: 'Reporting Client',
      compare: (a: Prescription, b: Prescription) => a.client.softwareTitle.localeCompare(b.client.softwareTitle),
      priority: 4
    },
    {
      title: 'Prescription Date',
      compare: (a: Prescription, b: Prescription) => a.prescriptionDate.getTime() - b.prescriptionDate.getTime(),
      priority: 1
    },    {
      title: 'Instructions',
      compare: (a: Prescription, b: Prescription) => a.instructions.localeCompare(b.instructions),
      priority: 9
    },

  ];
  constructor(
    private prescriptionService: PrescriptionService,
    private modalService: NzModalService,
    private viewContainerRef: ViewContainerRef) { }

  ngOnInit(): void {
    this.prescriptionService.getPrescriptions().subscribe(res => {
      this.loaded = true;
      this.data = res;
    });
  }

  showObjectModal(label: string, object: any) {
    this.modalService.create({
      nzTitle: label,
      nzContent: ObjectViewerModal,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        data: object
      }
    });
  }

}
