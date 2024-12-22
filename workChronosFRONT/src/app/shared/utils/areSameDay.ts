import { DateTime } from 'luxon';

/**
 * Compara duas datas e verifica se ambas são do mesmo dia.
 * 
 * A função ignora a hora e compara apenas as datas (ano, mês e dia).
 * 
 * @param {string} date1 - A primeira data no formato ISO 8601 (ex: '2024-12-22T14:30:00').
 * @param {string} date2 - A segunda data no formato ISO 8601 (ex: '2024-12-22T10:00:00').
 * 
 * @returns {boolean} Retorna `true` se as duas datas forem do mesmo dia, caso contrário, `false`.
 * 
 * @example
 * const date1 = '2024-12-22T14:30:00';
 * const date2 = '2024-12-22T10:00:00';
 * console.log(areSameDay(date1, date2)); // true se ambas forem do mesmo dia
 */
export const areSameDay = (date1?: string, date2?: string): boolean => {
  if (!date1 || !date2) return false
  const parsedDate1 = DateTime.fromFormat(date1, 'dd/MM/yyyy').toISODate(); 
  const parsedDate2 = DateTime.fromISO(date2).toISODate();

  return parsedDate1 === parsedDate2;
}