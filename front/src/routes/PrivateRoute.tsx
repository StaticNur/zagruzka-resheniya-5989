import { Navigate } from "react-router-dom";
import useAuthStore from "../store/auth";

interface PrivateRouteProps {
  children: React.ReactNode 
  allowedRoles: string
}
export function PrivateRoute ({ children, allowedRoles }: PrivateRouteProps) {
  const logout = useAuthStore(state => state.logout)
  const userRole = localStorage.getItem('role')

  if (!['admin', 'manager'].includes(userRole!)) {
    logout()
    return <Navigate to="/login" />;
  }

  if (!allowedRoles.includes(userRole!)) {
    return <Navigate to="/" />
  }

  return children;
}