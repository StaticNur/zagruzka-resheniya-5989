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
    Checkbox, List, ListItem
} from '@mui/material';
import React, { useEffect, useState } from 'react';
import axios from "../axios"
import { useNavigate, Link, useLocation } from "react-router-dom";
import { Parameter, Product } from "../types";

export function TotalAmountOfContract() {
    const location = useLocation();
    const { title } = location.state || {}; // Access the title from state

    const [totalAmount, setTotalAmount] = useState('');
    const [calculateSum, setCalculateSum] = useState('0');
    const [parameters, setParameters] = useState<Parameter[]>([]);
    const [product, setProduct] = useState<Product>();
    const [selectedParameters, setSelectedParameters] = useState<{ [key: string]: boolean }>({}); // Store selected parameters

    const handleChange = (e) => {
        const value = e.target.value;
        if (!isNaN(value) && value !== '') {
            setTotalAmount(value);
            calculateTotalSum(value, selectedParameters);
        } else {
            // Handle invalid input here, e.g., show an error message
            console.error('Invalid input: Please enter a valid number.');
        }
    };

    const handleParameterChange = (paramId) => {
        setSelectedParameters((prev) => {
            const newSelection = { ...prev, [paramId]: !prev[paramId] };
            calculateTotalSum(totalAmount, newSelection); // Recalculate sum when a parameter is selected/deselected
            return newSelection;
        });
    };

    const calculateTotalSum = (amount, selections) => {
        let sum = parseFloat(amount) || 0;

        for (const param of parameters) {
            if (selections[param.id]) {
                if (param.coefficientPositive < 1) {
                    sum *= (1 - param.coefficientPositive); // If selected, use positive coefficient
                } else {
                    sum *= param.coefficientPositive;
                }
            } else {
                if (param.coefficientNegative < 1) {
                    sum *= (1 - param.coefficientNegative); // If not selected, use negative coefficient
                } else {
                    sum *= param.coefficientNegative;
                }
            }
        }

        setCalculateSum(sum.toFixed(2)); // Update calculated sum with two decimal places
    };

    useEffect(() => {
        async function fetchParameterList() {
            try {
                const { data: products } = await axios.get<Product[]>('/api/products/search?name=' + title);
                if (products.length > 0) {
                    const { data: parametersParameters } = await axios.get<Parameter[]>('/api/products/' + products[0].id + '/parameters');
                    setParameters(parametersParameters);
                    setProduct(products[0]);
                } else {
                    console.error('No products found with the given title');
                }
            } catch (error) {
                console.error('Error fetching parameters:', error);
            }
        }
        fetchParameterList()
    }, [title])

    return (
        <MainLayout>
            <Box sx={{ width: '100%', paddingBottom: '30px' }}>
                {/* Условная отрисовка продукта */}
                {product ? (
                    <>
                        <Typography variant="h3" sx={{ marginBottom: '16px' }}>
                            Название: {product.name}
                        </Typography>
                        <List>
                            <ListItem sx={{ fontSize: '22px' }}>Описание продукта: {product.description || 'Нет описания'}</ListItem>
                            <ListItem sx={{ fontSize: '22px' }}>Описание для менеджера: {product.explanationForManager || 'Нет описания'}</ListItem>
                            <ListItem sx={{ fontSize: '22px', marginBottom: '26px' }}>Категория: {product.category}</ListItem>
                        </List>
                    </>
                ) : (
                    <Typography>Загрузка данных продукта...</Typography>
                )}

                <TextField
                    label="Введите исходную сумму стоимости продукта"
                    onChange={handleChange}
                    variant="outlined"
                    fullWidth
                    type="number"
                    value={totalAmount} // Set the value to controlled component
                    sx={{ marginBottom: '16px' }}
                />

                <Typography
                    variant="body1" // You can choose the variant according to your design
                    sx={{ marginBottom: '16px' }} // Styling to match your layout
                >
                    Выберите параметры, чтобы узнать итоговую сумму договора, которая получится, если контрагент пройдет по параметрам
                </Typography>



                {parameters.length > 0 ? (
                    <Box>
                        <Typography variant="h6" sx={{ marginBottom: '8px' }}>
                            Доступные параметры:
                        </Typography>
                        {parameters.map((param) => (
                            <Box key={param.id} sx={{ marginBottom: '8px' }}>
                                <FormControlLabel
                                    control={
                                        <Checkbox
                                            checked={selectedParameters[param.id] || false}
                                            onChange={() => handleParameterChange(param.id)}
                                        />
                                    }
                                    label={
                                        <>
                                            <Typography variant="body2">{param.name}: {param.description}</Typography>
                                            <Typography variant="body2">
                                                Коэффициенты: положительный {param.coefficientPositive}, отрицательный {param.coefficientNegative}
                                            </Typography>
                                        </>
                                    }
                                />
                            </Box>
                        ))}
                    </Box>
                ) : (
                    <Typography component="p" sx={{ fontSize: '18px', marginBottom: '16px' }}>
                        Нет доступных параметров.
                    </Typography>
                )}

                <TextField
                    label="Итоговая сумма договора"
                    variant="outlined"
                    fullWidth
                    type="number"
                    InputProps={{
                        readOnly: true, // Makes the field read-only
                    }}
                    value={calculateSum} // Set the value to controlled component
                    sx={{ marginBottom: '16px', marginTop: '24px' }}
                />

                <Link to="/new-agreement" state={{ title }}>
                    <Button
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            borderRadius: '12px',
                            padding: '9px 12px',
                            fontSize: '14px',
                            textTransform: 'inherit',
                        }}
                        variant="contained"
                        fullWidth
                    >
                        Далее
                    </Button>
                </Link>

            </Box>
        </MainLayout >
    )
}