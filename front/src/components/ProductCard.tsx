import { Add } from "@mui/icons-material";
import { Box, List, ListItem, Typography, Button } from "@mui/material";
import { Product } from "../types";

export function ProductCard ({ id, category, name, description }: Product) {
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
      <Box>
        <Typography fontSize={28} component="h4">
          { id }. { name }
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
      }} variant="contained" fullWidth >
        <Add />
        Дублировать
      </Button>
    </Box>
  )
}