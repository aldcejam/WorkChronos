import { DateTime } from "luxon";

export const formatDate = (date: string): string => {
  return DateTime.fromISO(date).toFormat('dd/MM/yyyy');
}

export const formatDateTime = (date: string): string => {
  return DateTime.fromISO(date).toFormat('dd/MM/yyyy HH:mm');
}

export const formatDuration = (date: string): string => {
  return DateTime.fromISO(date).toFormat('HH:mm');
}