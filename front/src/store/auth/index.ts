import { create } from "zustand";
import { Action, State } from "./types";

const useAuthStore = create<State & Action>((set) => ({
  isAuth: Boolean(localStorage.getItem('role')),
  login: (role) => {
    localStorage.setItem('role', role)
    set({ isAuth: true })
  },
  logout: () => {
    localStorage.removeItem('role')
    set({ isAuth: false })
  }
}))

export default useAuthStore