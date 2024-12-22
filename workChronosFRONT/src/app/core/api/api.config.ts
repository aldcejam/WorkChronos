export class API_CONFIG {
  private static readonly BASE_URL: 'https://localhost:8080';

  public static readonly ENDPOINTS = {
    USERS: '/users',
    ATTENDANCE_RECORD: {
      GET_BY_USER_ID: (id: string) => `${this.BASE_URL}/attendance-record/${id}`,
      START_DAY: (id: string) => `${this.BASE_URL}/attendance-record/${id}/start-day`,
      FINISH_DAY: (id: string) => `${this.BASE_URL}/attendance-record/${id}/finish-day`,
      START_BREAK: (id: string) => `${this.BASE_URL}/attendance-record/${id}/start-break`,
      FINISH_BREAK: (id: string) => `${this.BASE_URL}/attendance-record/${id}/finish-break`,
    },
  };
}
