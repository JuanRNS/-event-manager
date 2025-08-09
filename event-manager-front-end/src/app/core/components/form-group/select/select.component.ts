import { Component, input, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormsModule,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { IOptions } from '../../../interface/form.interface';
import { MatSelectModule } from '@angular/material/select';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-select',
  standalone: true,
  imports: [MatIconModule, ReactiveFormsModule, FormsModule, MatSelectModule],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: {
        appearance: 'outline',
      },
    },
  ],
  templateUrl: './select.component.html',
  styleUrl: './select.component.scss',
})
export class SelectComponent implements OnInit {
  public label = input.required<string>();
  public formControl = input.required<string>();
  public form = input.required<FormGroup>();
  public size = input<string>();
  public options = input.required<IOptions[] | Observable<IOptions[]>>();
  public optionsSelect: IOptions[] = [];

  private _options: IOptions[] = [];
  private readonly _subscriptions: Subscription[] = [];

  constructor() {}

  ngOnInit(): void {
    const options = this.options();
    if (options instanceof Observable) {
      const subscription = options.subscribe((options) => {
        this._options = options;
        this.optionsSelect = options;
      });
      this._subscriptions.push(subscription);
    } else if (Array.isArray(options)) {
      this._options = options;
      this.optionsSelect = options;
    }
  }

  public required(): boolean {
    return (
      this.form().get(this.formControl())?.hasValidator(Validators.required) ??
      false
    );
  }
}
