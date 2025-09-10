import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { MaskEnum } from '../enums/maskEnum';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Directive({
  selector: '[appMask]',
  providers: [
    { 
    provide: NG_VALUE_ACCESSOR, 
    useExisting: MaskDirective,
    multi: true 
  }]
})
export class MaskDirective implements ControlValueAccessor{
  @Input('appMask') tipo?: MaskEnum = MaskEnum.NOME;
  public valueUnmasked = '';

  private onChange: any = () => {};
  private onTouched: any = () => {};

  constructor(private readonly _el: ElementRef) {}
  writeValue(obj: any): void {
    this.valueUnmasked = obj;
    this._el.nativeElement.value = obj;
    this.onInput(false);
  }
  registerOnChange(fn: any): void {
    this.onChange = fn;
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  @HostListener('input') 
  onInput(executeChange = true) {
    let value = this._el.nativeElement.value;
    let valueNumber = value.replace(/\D/g, '');

    switch (this.tipo) {
      case MaskEnum.NOME:
        this.valueUnmasked = value;
        this._el.nativeElement.value = this.applyName(value);
        break;
      case MaskEnum.PHONE:
        this.valueUnmasked = valueNumber;
        this._el.nativeElement.value = this.applyTelefone(valueNumber);
        break;
      case MaskEnum.PIX:
        this.valueUnmasked = valueNumber;
        this._el.nativeElement.value = this.applyPixKey(valueNumber);
        break;
      default:
        this.valueUnmasked = value;
        this._el.nativeElement.value = value;
        break;
    }

    if(this.onChange && executeChange){
      this.onChange(this.valueUnmasked)
    };
  }

private applyPixKey(value: string): string {
    const cleanValue = value.replace(/\D/g, '');
    if (!cleanValue) return value;

    if (cleanValue.length === 11) {
        return cleanValue.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    }

    if (cleanValue.length === 14) {
        return cleanValue.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
    }
    return value;
}

private applyTelefone(value: string): string {
  
    if (value.length > 10) {
        return value.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    } else if (value.length > 6) {
        return value.replace(/(\d{2})(\d{4})(\d{1,4})/, '($1) $2-$3');
    } else if (value.length > 2) {
        return value.replace(/(\d{2})(\d{1,5})/, '($1) $2');
    }

    return value;
}
private applyName(value: string): string {
    return value
      .replace(/\d/g, '')
  }

}

