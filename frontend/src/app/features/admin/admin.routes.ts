import { Routes } from '@angular/router';import{authGuard}from'../../core/auth/guards/auth.guard';import{roleGuard}from'../../core/auth/guards/role.guard';

// Child feature routes will be added with the admin portal.
const modes=['categories','customers','orders','restaurants','riders','pending','feedback']as const;export const ADMIN_ROUTES: Routes = [{path:'',pathMatch:'full',redirectTo:'categories'},...modes.map(mode=>({path:mode,canActivate:[authGuard,roleGuard],data:{roles:['ADMIN'],mode},loadComponent:()=>import('./pages/admin-page/admin-page').then(m=>m.AdminPage)}))];
