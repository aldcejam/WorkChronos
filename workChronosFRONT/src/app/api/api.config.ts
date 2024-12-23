export class API_CONFIG {
  private static readonly BASE_URL = 'http://localhost:8080';

  public static readonly ENDPOINTS = {
    USERS: {
      GET_BY_ID: (id: string) => `${API_CONFIG.BASE_URL}/user/${id}`,
      LIST: `${API_CONFIG.BASE_URL}/user`,  
    },
    AUTH: {
      LOGIN: `${API_CONFIG.BASE_URL}/auth/login`,
      REGISTER: `${API_CONFIG.BASE_URL}/auth/register`,
    },
    ATTENDANCE_RECORD: {
      GET_LATEST_BY_USER_ID: (id: string) => `${API_CONFIG.BASE_URL}/attendance-record/${id}/latest`,
      LIST_BY_USER_ID: (id: string) => `${API_CONFIG.BASE_URL}/attendance-record/${id}/list`,
      START_DAY: (id: string) => `${API_CONFIG.BASE_URL}/attendance-record/${id}/start-day`,
      FINISH_DAY: (id: string) => `${API_CONFIG.BASE_URL}/attendance-record/${id}/finish-day`,
      START_BREAK: (id: string) => `${API_CONFIG.BASE_URL}/attendance-record/${id}/start-break`,
      FINISH_BREAK: (id: string) => `${API_CONFIG.BASE_URL}/attendance-record/${id}/finish-break`,
    },
  };
}
