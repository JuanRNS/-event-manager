import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { IResponseDashboard } from '../../../core/interface/dashboard.interface';
import { ApiService } from '../../services/api.service';
import { FormGroupArray } from '../../../core/interface/form.interface';
import { FormFieldEnum } from '../../../core/enums/formFieldEnum';
import { FormControl, FormGroup } from '@angular/forms';
import { FormComponent } from "../../../core/components/form-group/form/form.component";
import { ToastService } from '../../../core/services/toast.service';

@Component({
  selector: 'app-dashboard-date',
  standalone: true,
  imports: [
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
    FormComponent
],
  templateUrl: './dashboard-date.component.html',
  styleUrl: './dashboard-date.component.scss'
})
export class DashboardDateComponent{

  public listDashboard: IResponseDashboard [] = []
  public page = 0
  public pageSize = 3
  public totalElements = 0

  public form = new FormGroup({
    fromDate: new FormControl<string | null>(null),
    toDate: new FormControl<string | null>(null),
  });

  constructor(
    private readonly _service: ApiService,
    private readonly _toast: ToastService
  ) { }

  public get formGroupItens(): FormGroupArray{
    return [
      {
        component: FormFieldEnum.INPUT,
        label: 'Data Inicial',
        controlName: 'fromDate',
        type: 'date',
        size: '6',
      },
      {
        component: FormFieldEnum.INPUT,
        label: 'Data Final',
        controlName: 'toDate',
        type: 'date',
        size: '6',
      },
    ]
  }

  public downloadReport(id: number, name: string) {
    const from = new Date(this.form.value.fromDate ?? '').toISOString().substring(0, 10);
    const to = new Date(this.form.value.toDate ?? '').toISOString().substring(0, 10);
    this._service.getFileDownload(id, from, to).subscribe({
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

  public getDashBoardFromTo() {
    const from = new Date(this.form.value.fromDate ?? '').toISOString().substring(0, 10);
    const to = new Date(this.form.value.toDate ?? '').toISOString().substring(0, 10);

    this._service.getDashboardFromTo(from, to).subscribe({
      next: (res) => {
        this.listDashboard = res.content;
        this.totalElements = res.page.totalElements;
      },
      error: (err) => {
        this._toast.error(err.error.message || 'Erro ao carregar o dashboard');
      },
    });
  }

  public onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getDashBoardFromTo();
  }

}
