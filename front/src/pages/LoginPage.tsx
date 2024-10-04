import { Box } from "@mui/material";
import { LoginForm } from "../components/LoginForm";

export function LoginPage () {
  return (
    <Box sx={{ 
      height: '100vh',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    }}>
      <LoginForm />
    </Box>
  )
}