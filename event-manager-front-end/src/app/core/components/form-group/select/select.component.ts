import { Component, input } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, Validators } from '@angular/forms';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { IOptions } from '../../../interface/form.interface';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-select',
  standalone: true,
  imports: [
    MatIconModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule
  ],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: {
        appearance: 'outline'
      }
    }
  ],
  templateUrl: './select.component.html',
  styleUrl: './select.component.scss'
})
export class SelectComponent {
  public label = input.required<string>();
  public formControl = input.required<string>();
  public form = input.required<FormGroup>();  
  public options = input.required<IOptions[]>();

  public required(): boolean{
    return this.form().get(this.formControl())?.hasValidator(Validators.required) ?? false;
  }
}
