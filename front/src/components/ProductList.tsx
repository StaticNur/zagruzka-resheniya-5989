import { Search } from "@mui/icons-material";
import { Box, InputBase } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "../axios"
import { Product } from "../types";

export function ProductList () {
  const [products, setProducts] = useState<Product[]>([]) 

  useEffect(() => {
    async function fetchProductList () {
      const data = axios.get('/api/products/all')
      // setProducts(data)
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

      </Box>
    </Box>
  )
}