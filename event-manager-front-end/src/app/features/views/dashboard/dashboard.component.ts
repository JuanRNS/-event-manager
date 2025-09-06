import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { IResponseDashboard } from '../../../core/interface/dashboard.interface';
import { ApiService } from '../../services/api.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule, 
    RouterModule, 
    MatIconModule,
    MatPaginatorModule,
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit{
  public listDashboard: IResponseDashboard [] = [];
  public page = 0;
  public pageSize = 3;
  public totalElements = 0;

  constructor(
    private readonly _service: ApiService,
  ) { }

  ngOnInit(): void {
    this.getListDashboard();
  }

  public getListDashboard() {
    this._service.getListDashboard(this.page, this.pageSize).subscribe({
      next: (res) => {
        this.listDashboard = res.content;
        this.totalElements = res.page.totalElements;
      },
      error: (err) => {
        console.error('Erro ao carregar o dashboard', err);
      },
    });
  }

  public downloadReport(id: number, name: string) {
    this._service.getFileDownload(id).subscribe({
      next: (res) => {
      const blob = new Blob([res], { type: 'application/pdf' });
      const url = URL.createObjectURL(blob);

      const link = document.createElement('a');
      link.href = url;
      link.target = '_blank';
      link.download = `relatorio_garcom_${name}.pdf`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);

      URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.error('Erro ao gerar o relatório', err);
      },
    });
  }

  public onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getListDashboard();
  }
}

