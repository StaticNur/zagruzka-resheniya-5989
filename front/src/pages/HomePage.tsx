import { Header } from "../components/Header";
import useAuthStore from "../store/auth";

export function HomePage () {
  const logout = useAuthStore(state => state.logout)
  const userRole = localStorage.getItem('role');

  return (
    <>
      <Header />
      {userRole === 'admin' ? <h1>ADMIN PANEL</h1> : <h1>MANAGER PANEL</h1>}
      <button onClick={() => logout()}>logout</button>
    </>
  )
}