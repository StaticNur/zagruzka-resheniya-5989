import { BrowserRouter } from 'react-router-dom'
import { createRoot } from 'react-dom/client'
import { CssBaseline, ThemeProvider } from "@mui/material";

import App from './App.tsx'
import './index.css'
import MyTheme from './MyTheme.ts';

createRoot(document.getElementById('root')!).render(
  <BrowserRouter>
    <ThemeProvider theme={MyTheme}>
      <CssBaseline />
      <App />
    </ThemeProvider>
  </BrowserRouter>,
)
