import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { Api, SlaPolicy } from '../../../../core/services/api';
/**
 * SLA policies display component.
 */
@Component({
  selector: 'app-sla-management',
  standalone: true,
  imports: [CommonModule, TableModule, CardModule],
  templateUrl: './sla-management.component.html'
})
export class SlaManagementComponent implements OnInit {
  policies: SlaPolicy[] = [];

  constructor(private api: Api) {}

  ngOnInit() {
    this.api.getSlaPolicies().subscribe(data => this.policies = data);
  }
}