import { DateTime } from "luxon";

export const generateGreeting = () => {
    const currentHour = DateTime.local().hour;
    if (currentHour >= 5 && currentHour < 12) return 'Bom dia';
    if (currentHour >= 12 && currentHour < 18) return 'Boa tarde';
    return 'Boa noite';
}  
    