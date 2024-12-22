import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '', // Rota principal
        loadChildren: () =>
          import('./pages/pages.module').then((m) => m.PagesModule), // Certifique-se de apontar para o arquivo correto
      },
      { path: '**', redirectTo: '' },
];
