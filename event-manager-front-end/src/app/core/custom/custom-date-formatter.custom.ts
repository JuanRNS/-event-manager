import { CalendarDateFormatter, DateFormatterParams } from 'angular-calendar';
import { formatDate } from '@angular/common';
import { Injectable } from '@angular/core';

@Injectable()
export class CustomDateFormatter extends CalendarDateFormatter {
  public override weekViewTitle({ date, locale = 'pt-BR' }: DateFormatterParams): string {
    return formatDate(date, 'MMMM yyyy', locale);
  }

  public override weekViewHour({ date, locale = 'pt-BR' }: DateFormatterParams): string {
    return formatDate(date, 'HH:mm', locale);
  }

  public override dayViewHour({ date, locale = 'pt-BR' }: DateFormatterParams): string {
    return formatDate(date, 'HH:mm', locale); 
  }
}