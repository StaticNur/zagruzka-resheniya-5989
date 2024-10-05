import {
  AppBar,
  Box,
  Button,
  Container,
  IconButton,
  InputBase,
  List,
  ListItem,
  Typography,
} from '@mui/material'
import logo from '../assets/img/logo.png'
import { KeyboardArrowDown, Person, Search } from '@mui/icons-material'
import useAuthStore from '../store/auth'

export function Header() {
  const logout = useAuthStore(state => state.logout)

  function logoutHandler () {
    if (window.confirm('Вы действительно хотите выйти?')) {
      return logout()
    } else { 
      return
    }
  }

  return (
    <>
      <AppBar position="static" color="primary" style={{ boxShadow: 'none' }}>
        <Container style={{ maxWidth: '1260px', padding: '0' }}>
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'space-between',
              padding: '16px 0px',
            }}
          >
            <List sx={{ display: 'flex', gap: '24px' }}>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                Частным лицам
              </ListItem>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                Юридическим лицам
              </ListItem>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                Партнерам
              </ListItem>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                VIP
              </ListItem>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                Согласие-вита
              </ListItem>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                Компания
              </ListItem>
              <ListItem
                sx={{
                  color: 'white',
                  width: 'auto',
                  fontSize: '16px',
                  padding: 0,
                }}
              >
                Офисы
              </ListItem>
            </List>
            <Box display="flex" alignItems="center">
              <Box
                sx={{
                  width: '300px',
                  borderRadius: 2,
                  padding: 2,
                  border: '1px solid white',
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                }}
              >
                <InputBase
                  placeholder="Поиск"
                  inputProps={{ 'aria-label': 'search' }}
                  sx={{ width: '100%', color: '#fff' }}
                />
                <Search style={{ cursor: 'pointer' }} fontSize="medium" />
              </Box>
              <IconButton edge="end" color="inherit" style={{ marginLeft: 16 }}>
                <Person fontSize="large"  />
              </IconButton>
              <Typography onClick={logoutHandler} style={{ cursor: 'pointer' }} component="a" fontSize={16}>
                выйти
              </Typography>
            </Box>
          </Box>
        </Container>
      </AppBar>
      <Container style={{ maxWidth: '1260px'}}>
        <Box
          display="flex"
          justifyContent="space-between"
          alignItems="center"
          sx={{ backgroundColor: '#f5f5f5', padding: '20px 0 80px' }}
        >
          <Box sx={{ display: 'flex', alignItems: 'center', gap: '70px' }}>
            <img src={logo} alt="logo" />
            <Box display={'flex'}>
              <Box mr={6}>
                <Typography variant="body1" sx={{ fontSize: '22px', fontWeight: 600 }}>
                  +7 900 555 11 55
                </Typography>
                <Typography variant="body2" sx={{ color: '#9D9D9D', fontSize: '16px', display: 'flex', alignItems: 'center' }}>
                  Москва
                  <KeyboardArrowDown style={{ color: '#9d9d9d'}} />
                </Typography>
              </Box>
              <Box>
                <Typography variant="body1" sx={{ fontSize: '22px', fontWeight: 600 }}>
                  +7 495 739 01 01
                </Typography>
                <Typography variant="body2" sx={{ color: '#9D9D9D', fontSize: '16px' }}>
                  Офисы
                </Typography>
              </Box>
            </Box>
          </Box>
          <Box>
            <Button
              variant="outlined"
              sx={{
                borderRadius: '40px',
                borderColor: 'primary',
                color: 'black',
                fontSize: '20px',
                textTransform: 'none',
                lineHeight: '1',
                marginRight: '12px',
                padding: '14px 24px',
              }}
            >
              Купить полис
            </Button>
            <Button
              variant="outlined"
              sx={{
                borderRadius: '40px',
                borderColor: 'primary',
                color: 'black',
                fontSize: '20px',
                textTransform: 'none',
                lineHeight: '1',
                padding: '14px 24px',
              }}
            >
              Страховой случай
            </Button>
          </Box>
        </Box>
      </Container>
    </>
  )
}
