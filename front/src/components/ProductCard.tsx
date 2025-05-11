import { Add } from "@mui/icons-material";
import { useNavigate } from 'react-router-dom';
import { Box, List, ListItem, Typography, Button } from "@mui/material";
import { Product } from "../types";
import axios from "../axios"

export function ProductCard({ id, name, description }: Product) {
  const navigate = useNavigate(); // Используем useNavigate для навигации

  const handleDuplicateClick = () => {
    // Навигация на нужную страницу при клике
    navigate('/total-amount-of-contract', { state: { title: name } });
  };

  async function clickHandler() {
    event.stopPropagation(); 
    window.location.reload();
    try {
      await axios.post(`/api/products/duplicate/${id}`);
    } catch (err) {
      alert('An error occurred: ' + err.message); // Display the error message to the user
    }
  }


  return (
    <Box sx={{
      display: 'flex',
      alignItems: 'flex-start',
      justifyContent: 'space-between',
      gap: '20px',
      background: '#fff',
      borderRadius: '12px',
      padding: '34px 22px'
    }}>
      <Box  onClick={handleDuplicateClick} >
        <Typography fontSize={28} component="h4">
          {id}. {name}
        </Typography>
        <List>
          <ListItem>
            Описание продукта: {description}
          </ListItem>
          <ListItem>
            категория: ACCIDENT
          </ListItem>
        </List>
      </Box>
      <Button sx={{
        display: 'flex',
        alignItems: 'center',
        borderRadius: '12px',
        padding: '9px 12px',
        maxWidth: '140px',
        fontSize: '14px',
        textTransform: 'inherit'
      }} variant="contained"
        fullWidth
        onClick={clickHandler}>
        <Add />
        Дублировать
      </Button>
    </Box>
  )
}