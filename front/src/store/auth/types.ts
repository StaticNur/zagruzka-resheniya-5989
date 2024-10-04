export type State = {
  isAuth: boolean
}

export type Action = {
  logout: () => void
  login: (role: string) => void
}