import { Box, Button, Checkbox, FormControlLabel, FormHelperText, IconButton, TextField, Typography } from '@mui/material';
import mailIcon from "../assets/icons/mail-ru.png"
import googleIcon from "../assets/icons/google.png"
import whatsAppIcon from "../assets/icons/whatsApp.png"
import yandexIcon from "../assets/icons/yandex.png"
import { SubmitHandler, useForm } from 'react-hook-form';
import useAuthStore from '../store/auth';
import { useNavigate } from 'react-router-dom';

type FormValues = {
  email: string
  password: string
  checkbox: boolean
}

export function LoginForm () {
  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm<FormValues>({
    mode: 'onBlur'
  })
  const navigate = useNavigate()
  const login = useAuthStore(state => state.login)

  const submitHandler: SubmitHandler<FormValues> = ({ email, password }) => {
    if (email === 'admin@a.ru' && password === '12345678') {
      login('admin')
      return navigate('/')
    } else if (email === 'manager@m.ru' && password === '12345678') {
      login('manager')
      return navigate('/')
    } else {
      return alert('Неверный логин или пароль')
    }
  } 

  return (
    <Box
      component="form"
      onSubmit={handleSubmit(submitHandler)}
      sx={{
        width: 620,
        margin: '0 auto',
        padding: 7,
        background: '#fff',
        borderRadius: '14px',
      }}
    >
      <Typography variant="h4" fontSize={28} fontWeight="600" marginBottom={1}>
        Войти
      </Typography>
      <Typography variant="body2" marginBottom={4}>
        <Typography color={'secondary'} component={'span'}>Нет аккаунта?</Typography> Зарегистрироваться 
      </Typography>

      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          gap: 3
        }}  
      > 
        {errors?.email && <FormHelperText style={{ color: '#D80000' }}>{ errors.email.message || 'Ошибка!' }</FormHelperText>}
        <TextField 
          {...register('email', {
            required: 'Это поле обязательное!'
          })}
          label="Email" 
          type='email'
          variant="outlined" 
          fullWidth 
        />
        {errors?.password && <FormHelperText style={{ color: '#D80000' }}>{ errors.password.message || 'Ошибка!' }</FormHelperText>}
        <TextField 
          {...register('password', {
            required: 'Это поле обязательное!',
            minLength: {
              value: 8,
              message: 'Минимальное количество символов - 8'
            }
          })}
          label="Пароль" 
          variant="outlined" 
          type="password" 
          fullWidth 
        />

        <FormControlLabel
          control={<Checkbox />}
          label="Запомнить меня"
          sx={{ alignSelf: 'flex-start', color: errors.checkbox ? '#D80000' : '' }}
        />

        <Box sx={{ display: 'flex', alignItems: 'center', gap: '4px' }}>
          <span className='line' />
          <Typography variant="body2">
            или
          </Typography>
          <span className='line' />
        </Box>

        <Box sx={{ display: 'flex', justifyContent: 'center', gap: 2 }}>
          <IconButton>
            <img src={mailIcon} alt="mail" />
          </IconButton>
          <IconButton>
            <img src={whatsAppIcon} alt="whatsApp" />
          </IconButton>
          <IconButton>
            <img src={yandexIcon} alt="yandex" />
          </IconButton>
          <IconButton>
            <img src={googleIcon} alt="google" />
          </IconButton>
        </Box>

        <Button
          variant="contained"
          fullWidth
          type='submit'
          sx={{
            backgroundColor: 'primary',
            fontSize: '18px',
            padding: 2,
            color: '#fff',
            '&:hover': {
              backgroundColor: '#FF6D00',
            },
          }}
        >
          Войти
        </Button>
      </Box>
    </Box>
  );
};

