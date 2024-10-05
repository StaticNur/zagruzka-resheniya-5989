import { Box } from "@mui/material";
import { FilterProducts } from "../components/FilterProducts";
import { MainLayout } from "../layouts/MainLayout";
import { AddCircleOutline } from "@mui/icons-material";
import { ProductList } from "../components/ProductList";

export function HomePage () {
  const userRole = localStorage.getItem('role');

  const titleElement = userRole === 'admin' ? 
    <>
      Список всех доступных фильтров
      <AddCircleOutline style={{ 
        color: '#B0B0B0',
        marginLeft: '14px'
      }} />
    </>
  : 'Manager'

  return (
    <MainLayout title={titleElement}>
      {userRole === 'admin' && 
        <Box display={'flex'}>
          <FilterProducts />
          <ProductList />
        </Box>
      }
    </MainLayout>
  )
}