/* global app */

app.controller("MainController", function ($scope) {
    $scope.title = 'Belajar Angular';
    $scope.book = {
        title: 'Belajar AngularJs Bersama Agung Setiawan',
        author: 'Agung Setiawan',
        price: 875000,
        pubdate: new Date('2015', '09', '01')
    };

    $scope.books = [
        {
            title: 'Belajar AngularJs Bersama Agung Setiawan',
            author: 'Agung Setiawan',
            price: 80000,
            pubdate: new Date('2015', '09', '09'),
            likes: 0
        },
        {
            title: 'Belajar Ruby',
            author: 'Penulis Satu',
            price: 65000,
            pubdate: new Date('2015', '10', '09'),
            likes: 0
        },
        {
            title: 'Belajar Java',
            author: 'Penulis Dua',
            price: 85000,
            pubdate: new Date('2015', '10', '09'),
            likes: 0
        }
    ];

    $scope.logToConsole = function (index) {
        var book = $scope.books[index];
        console.log(book);
    };
    
    $scope.likes = function (index) {
        $scope.books[index].likes += 1;
    };
});
