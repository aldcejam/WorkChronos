export const API_CONFIG = {
    BASE_URL: 'https://localhost:8080',
    ENDPOINTS: {
      USERS: '/users',
      ATTENDANCE_RECORD: {
        GET_BY_USER_ID: (userID: string) => `/attendance-record/${userID}`,
        START_DAY: (userID: string) => `/attendance-record/${userID}/start-day`,
        FINISH_DAY: (userID: string) => `/attendance-record/${userID}/end-day`,
        START_BREAK: (userID: string) => `/attendance-record/${userID}/start-break`,
        FINISH_BREAK: (userID: string) => `/attendance-record/${userID}/end-break`,
      },
    },
  };
  