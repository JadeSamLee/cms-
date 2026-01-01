import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Api, Team } from '../../../../core/services/api';
import { MessageService } from 'primeng/api';

/**
 * Teams management with reactive form and validation.
 */
@Component({
  selector: 'app-teams-management',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TableModule,
    InputTextModule,
    ButtonModule,
    CardModule
  ],
  templateUrl: './teams-management.component.html'
})
export class TeamsManagementComponent implements OnInit {
  teams: Team[] = [];
  teamForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    description: ['']
  });

  constructor(
    private api: Api,
    private fb: FormBuilder,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.api.getTeams().subscribe(data => this.teams = data);
  }

  createTeam() {
    if (this.teamForm.valid) {
      this.api.createTeam(this.teamForm.value).subscribe(newTeam => {
        this.teams.push(newTeam);
        this.teamForm.reset();
        this.messageService.add({
          severity: 'success',
          summary: 'Team Created',
          detail: newTeam.name
        });
      });
    }
  }
}