import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ColumnItem } from "../../../shared/interfaces/column-item.interface";
import { Patient, Pharmacy, Prescriber, Prescription } from "../../interfaces/prescription.interface";
import { PrescriptionService } from "../../services/prescription.service";
import { NzModalService } from "ng-zorro-antd/modal";
import { ObjectViewerModal } from "../../../shared/components/object-viewer-modal/object-viewer-modal.component";
import * as _ from 'lodash';
import { Client } from "../../../shared/interfaces/client.interface";

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
    }, {
      title: 'Instructions',
      compare: (a: Prescription, b: Prescription) => a.instructions.localeCompare(b.instructions),
      priority: 9
    },

  ];

  constructor(
    private prescriptionService: PrescriptionService,
    private modalService: NzModalService,
    private viewContainerRef: ViewContainerRef) {
  }

  ngOnInit(): void {
    this.prescriptionService.getPrescriptions().subscribe(res => {
      this.loaded = true;
      this.data = res;
    });
  }

  flattenAddressIntoObject(object: any, key: string): any {
    if (!key) {
      return object;
    }
    console.log(object);
    console.log(key);
    const newObj = { ...object };
    newObj['Street'] = _.startCase(object[key].street.toLowerCase());
    newObj['City'] = _.startCase(object[key].city.toLowerCase());
    newObj['State'] = _.startCase(object[key].state.toLowerCase());
    newObj['Zip Code'] = _.startCase(object[key].zipCode.toLowerCase());
    delete newObj[key];
    return newObj;
  }

  showInstructionsModal(instructions: string) {
    this.modalService.create({
      nzTitle: 'Instructions',
      nzContent: instructions || 'N/A',
      nzViewContainerRef: this.viewContainerRef,
    });
  }

  showObjectModal(label: string, addressKey: string, object: Patient | Prescriber | Pharmacy | Client) {
    this.modalService.create({
      nzTitle: label,
      nzContent: ObjectViewerModal,
      nzViewContainerRef: this.viewContainerRef,
      nzComponentParams: {
        data: this.flattenAddressIntoObject(object, addressKey)
      }
    });
  }

}
