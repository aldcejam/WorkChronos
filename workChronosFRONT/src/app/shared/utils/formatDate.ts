import { DateTime, Duration } from "luxon";

export const formatDate = (date: string): string => {
  return DateTime.fromISO(date).toFormat('dd/MM/yyyy');
}

export const formatDateTime = (date: string): string => {
  return DateTime.fromISO(date).toFormat('HH:mm dd/MM/yyyy');
}

export const formatDuration = (date: string): any => {
  return Duration.fromISO(date);
}