import {
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Checkbox,
  Select,
  MenuItem,
  InputLabel,
  Typography,
  Box,
} from '@mui/material'

export function FilterProducts() {
  return (
    <Box sx={{
      width: '100%',
      maxWidth: '500px'
    }}>
      <Typography variant="h4" sx={{ fontSize: '28px', fontWeight: 600, marginBottom: '28px' }}>
        Фильтр
      </Typography>

      {/* Пол (Select) */}
      <Box>
        <FormControl fullWidth sx={{ marginBottom: '24px', width: '300px', background: 'white' }}>
          <InputLabel style={{ border: 'none'}} id="gender-select-label">Пол</InputLabel>
          <Select labelId="gender-select-label" defaultValue="">
            <MenuItem value="male">Мужской</MenuItem>
            <MenuItem value="female">Женский</MenuItem>
          </Select>
        </FormControl>
      </Box>

      <Box>
        <FormControl component="fieldset" sx={{ marginBottom: '24px' }}>
          <RadioGroup aria-label="age-group" defaultValue="">
            <FormControlLabel
              value="under18"
              control={<Radio />}
              label="до 18 лет"
            />
            <FormControlLabel
              value="18-25"
              control={<Radio />}
              label="18-25 лет"
            />
            <FormControlLabel
              value="26-45"
              control={<Radio />}
              label="26-45 лет"
            />
            <FormControlLabel
              value="46-65"
              control={<Radio />}
              label="46-65 лет"
            />
            <FormControlLabel
              value="65plus"
              control={<Radio />}
              label="65+ лет"
            />
          </RadioGroup>
        </FormControl>
      </Box>

      <FormControlLabel
        style={{ display: 'block' }}
        control={<Checkbox />}
        label="Водительское удостоверение"
      />
      <FormControlLabel control={<Checkbox />} label="Высшее образование" />

      <Typography variant="h2" sx={{ 
        marginTop: '35px', 
        marginBottom: '16px',
        fontWeight: 600,
        fontSize: 24
      }}>
        Выбор категории
      </Typography>

      <FormControlLabel style={{ display: 'block' }} control={<Checkbox />} label="недвижимость" />
      <FormControlLabel style={{ display: 'block' }} control={<Checkbox />} label="здоровье" />
      <FormControlLabel style={{ display: 'block' }} control={<Checkbox />} label="автомобиль" />
      <FormControlLabel style={{ display: 'block' }} control={<Checkbox />} label="имущество" />
      <FormControlLabel style={{ display: 'block' }} control={<Checkbox />} label="путешествия" />
    </Box>
  )
}
