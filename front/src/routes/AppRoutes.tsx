import { Navigate, Route, Routes } from "react-router-dom"
import useAuthStore from "../store/auth"
import { LoginPage } from "../pages/LoginPage"
import { HomePage } from "../pages/HomePage"
import { PrivateRoute } from "./PrivateRoute"
import { CreateProduct } from "../pages/CreateProduct"

export function AppRoutes () {
  const isAuth = useAuthStore(state => state.isAuth)
  const role = localStorage.getItem('role')

  if (isAuth) {
    return (
      <Routes>
        <Route 
          path="/"
          element={
            <PrivateRoute allowedRoles={role!}>
              <HomePage />
            </PrivateRoute>
          }
        />
        <Route 
          path="/create-product"
          element={
            <PrivateRoute allowedRoles="admin">
              <CreateProduct />
            </PrivateRoute>
          }
        />
        <Route 
          path="/list-manager"
          element={
            <PrivateRoute allowedRoles="manager">
              <h1>List of manager</h1>
            </PrivateRoute>
          }
        />
      </Routes>
    )
  } else {
    return (
      <Routes>
        <Route element={<LoginPage />} path="/login" />
        <Route element={<Navigate to={'/login'} />} path="*" />
      </Routes>
    )
  }
}