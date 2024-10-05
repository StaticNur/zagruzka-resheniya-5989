import { Add, Search } from "@mui/icons-material";
import { Box, Button, InputBase, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "../axios"
import { Product } from "../types";
import { ProductCard } from "./ProductCard";
import { Link } from "react-router-dom";

export function ProductList () {
  const [products, setProducts] = useState<Product[]>([]) 

  useEffect(() => {
    async function fetchProductList () {
      const { data } = await axios.get<Product[]>('/api/products/all')
      setProducts(data)
    }
    fetchProductList()
  }, [])

  return (
    <Box style={{
      width: '100%'
    }}>
      <Box sx={{
        display: 'flex',
        gap: '7px',
        alignItems: 'center',
        width: '100%',
        background: '#fff',
        borderRadius: '12px',
        padding: '16px',
        marginBottom: "24px"
      }}>
        <Search  style={{ cursor: 'pointer', color: 'a2a2a2' }} fontSize="medium" />
        <InputBase
          placeholder="Поиск по названию"
          inputProps={{ 'aria-label': 'search' }}
          sx={{ width: '100%', fontSize: '14px' }}
        />
      </Box>
      <Box sx={{
        display: 'flex',
        flexDirection: 'column',
        gap: '16px'
      }}>
        {products.length > 0 ? 
          products.map((product) => <ProductCard key={product.id} {...product}  />)
          :
          <>
            <Typography align="center" fontSize={24} component="p">
              Список доступных продуктов пуст. Добавьте хотя бы 1 продукт.
            </Typography>
            <Link to="/create-product">
              <Button sx={{
                display: 'flex',
                alignItems: 'center',
                borderRadius: '12px',
                padding: '9px 12px',
                // maxWidth: '140px',
                fontSize: '14px',
                textTransform: 'inherit'
              }} variant="contained" fullWidth >
                <Add />
                Создать продукт
              </Button>
            </Link>
          </>
        }
      </Box>
    </Box>
  )
}