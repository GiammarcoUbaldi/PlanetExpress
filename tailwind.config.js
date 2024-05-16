/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
      "./src/main/resources/templates/**/*.html",
      "./src/main/resources/templates/*.html",
    "./src/main/resources/templates/**/**/*.html"
  ],
    theme: {
        extend: {
            colors: {
                'verde1': '#004322',
                'giallo1': '#FED860',
                'verde2': '#BCE3A0',
                'verde4': '#96BA14',
                'bianco1': '#F8E7C2',
                'rosso1': '#F3896B',
                'celeste1': '#80D6E5',
                'celeste2': '#9FDAE0',
                'giallo2': '#FFD741',
                'verdeSuccessoBG': '#BCF0B4',
                'verdeSuccessoText': '#3B693A',
                'rossoInSuccessoBg': '#FFDBC9',
                'rossoInSuccessoText': '#341000',
                'gialloSecondarioText': '#261A00',
                'gialloSecondarioBG': '#FFE093',
                'bianco2': '#FFF7DF',
                'viola1': '#8E80E5',
            },
        },
    },
  plugins: [],
}


