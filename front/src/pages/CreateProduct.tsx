import { Add } from "@mui/icons-material";
import { MainLayout } from "../layouts/MainLayout";
import {
  Box,
  Typography,
  TextField,
  FormControl,
  FormControlLabel,
  RadioGroup,
  Radio,
  Button,
} from '@mui/material';
import { useState } from "react";
import axios from "../axios"
import { useNavigate } from "react-router-dom";

export function CreateProduct () {
  const navigate = useNavigate()
  const [title, setTitle] = useState('')
  const [desc, setDesc] = useState('')

  async function clickHandler () {
    try {
      const body = {
        name: title,
        description: desc,
        explanationForManager: "описание этого продукта для менеджера",
        category: {
          id: 1
        },
        parameters: [
          {
            id: 1
          },
          {
            id: 2
          }
        ]
      }
      await axios.post('/api/products', body)
      navigate('/')
    } catch (err) {
      alert(err)
    }
  }

  return (
    <MainLayout title="Создание нового продукта">
      <Box sx={{ width: '100%', paddingBottom: '30px' }}>
      <TextField
        label="Введите название продукта"
        onChange={(e) => setTitle(e.target.value)}
        variant="outlined"
        fullWidth
        sx={{ marginBottom: '16px' }}
      />

      <TextField
        label="Введите описание продукта"
        variant="outlined"
        onChange={(e) => setDesc(e.target.value)}
        fullWidth
        multiline
        rows={3}
        sx={{ marginBottom: '16px' }}
      />

      <TextField
        label="Текст для менеджера*"
        variant="outlined"
        fullWidth
        sx={{ marginBottom: '60px' }}
      />

      <Box sx={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'flex-start'
      }}>
        <Box sx={{ maxWidth: '620px' }}>
          <Typography variant="h3" sx={{ fontSize: '24px', marginBottom: '16px' }}>
            Выбранные параметры продукта
          </Typography>

          <Typography variant="h6" sx={{ fontSize: '20px', marginBottom: '8px' }}>
            1. Возраст
          </Typography>

          <FormControl component="fieldset" sx={{ marginBottom: '8px' }}>
            <RadioGroup name="age">
              <FormControlLabel value="до 18 лет" control={<Radio />} label="до 18 лет" />
              <FormControlLabel value="18-25 лет" control={<Radio />} label="18-25 лет" />
              <FormControlLabel value="26-45 лет" control={<Radio />} label="26-45 лет" />
              <FormControlLabel value="46-65 лет" control={<Radio />} label="46-65 лет" />
              <FormControlLabel value="65+ лет" control={<Radio />} label="65+ лет" />
            </RadioGroup>
          </FormControl>
          <Typography sx={{ marginBottom: '24px' }}>значение коэффициента 0,5</Typography>

          <Typography variant="h6" sx={{ fontSize: '20px', marginBottom: '8px' }}>
            2. Наличие ВП
          </Typography>

          <TextField
            label="Критерий"
            variant="outlined"
            fullWidth
            sx={{ marginBottom: '8px' }}
          />
          <Typography sx={{ marginBottom: '24px' }}>значение коэффициента 2</Typography>

          <Typography variant="h6" sx={{ fontSize: '20px', marginBottom: '8px' }}>
            3. Наличие высшего образования
          </Typography>

          <TextField
            label="Критерий"
            variant="outlined"
            fullWidth
            sx={{ marginBottom: '8px' }}
          />
          <Typography sx={{ marginBottom: '24px' }}>значение коэффициента 2</Typography>
        </Box>
        <Box sx={{ maxWidth: '620px', width: '100%' }}>
          <Typography variant="h3" sx={{ fontSize: '24px', marginBottom: '16px' }}>
            Список доступных параметров
          </Typography>
          <Typography component="p" sx={{ fontSize: '18px', marginBottom: '16px' }}>
            К сожелению список параметров пуст. Добавьте хотя бы 1 параметр
          </Typography>
          <Button variant="contained" color="primary" sx={{ marginTop: '16px', textTransorm: 'none', borderRadius: '12px' }}>
            <Add />
            Создать новый параметр
          </Button>
        </Box>
      </Box>
        <Button variant="contained" color="primary" sx={{ marginTop: '16px', textTransorm: 'none', borderRadius: '12px' }} onClick={clickHandler}>
            <Add />
            Создать новый продукт
          </Button>
    </Box>
    </MainLayout >
  )
}