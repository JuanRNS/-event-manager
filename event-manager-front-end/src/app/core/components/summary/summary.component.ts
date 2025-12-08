import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ApiService } from '../../../features/services/api.service';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-summary',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './summary.component.html',
  styleUrl: './summary.component.scss',
})
export class SummaryComponent implements OnInit {
  public totalEmployees = 0;
  public totalValue = 0;
  public totalParty = 0;

  constructor(
    private readonly _service: ApiService,
    private readonly _toast: ToastService
  ) {}

  ngOnInit(): void {
    this.getSummary();
  }

  public getSummary() {
    this._service.getDashboardSummary().subscribe({
      next: (res) => {
        this.totalEmployees = res.totalEmployees;
        this.totalParty = res.totalParties;
      },
      error: (err) => {
        this._toast.error(
          err.error.message || 'Erro ao carregar o resumo do dashboard.'
        );
      },
    });
  }
}
