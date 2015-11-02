'use strict';

$(function(){

    // fill up wallet
    $('.fill-up').on('click', function(){
        var fillUpPrompt = prompt('Insert money value: ', '');
        var fillUp = parseInt(fillUpPrompt);
        if (fillUp > 0) {
            $.ajax({
                url: '/rest/wallet/fillup',
                type: 'post',
                data: fillUp + '',
                success: function(money) {
                    $('#wallet').text('Money: ' + money + ' $');
                }
            });
        }
    });

    // controllers
    $('#bet').on('click', function() {
        var bet = $('#bet-val').val() + '';
        $.ajax({
            url: '/rest/bet',
            type: 'post',
            data: bet,
            success: function(gameState) {
                $('#bet-value').text('Bet: ' + bet + ' $');
                updatePlayerMoney();
                updateView(gameState);
            }
        })
    });

    $('#hit').on('click', function() {
        $.ajax({
            url: '/rest/hit',
            type: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function(gameState) {
                updateView(gameState);
            }
        })
    });

    $('#stand').on('click', function() {
        $.ajax({
            url: '/rest/stand',
            type: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function(gameState) {
                updateView(gameState);
            }
        });
    });


    // functions
    function updateView(gameState) {
        updateStatus(gameState);
        updateScores(gameState);
        showCards(gameState);
        if (gameState.player.gameResult === 'CONTINUE') {
            showGameController();
        } else {
            updatePlayerMoney();
            showBetController();
        }
    }

    function updateStatus(gameState) {
        $('#dealer-status').text('Status: ' + gameState.dealer.gameResult);
        $('#player-status').text('Status: ' + gameState.player.gameResult);
    }

    function updatePlayerMoney() {
        $.ajax({
            url: '/rest/wallet/money',
            type: 'get',
            success: function(money) {
                $('#wallet').text('Money: ' + money + ' $');
            }
        });
    }

    function updateScores(gameState) {
        $('#dealer-score').text('Score: ' + gameState.dealer.score);
        $('#player-score').text('Score: ' + gameState.player.score);
    }

    function showCards(gameState) {
        var playerCards = $('.center-wrapper', $('.player-cards'));
        var playerHand = gameState.player.hand;
        showParticipantCards(playerCards, playerHand);

        var dealerCards = $('.center-wrapper', $('.dealer-cards'));
        var dealerHand = gameState.dealer.hand;
        showParticipantCards(dealerCards, dealerHand);
    }

    function showParticipantCards(participantCards, hand) {
        participantCards.empty();
        for (var i = 0; i < hand.length; i++) {
            var playerCardImg = buildCardImage(hand[i]);
            participantCards.append(playerCardImg);
        }
        participantCards.css('width', hand.length * 100 + 'px');
    }

    function buildCardImage(card) {
        var cardPath = '/resources/images/cards/';
        var cardImage = "<div class='card'><img src='";
        if (card) {
            cardPath += card.toLowerCase()+ ".gif";
            cardImage += cardPath +"' alt='" + card + "'></div>";
        }
        return cardImage;
    }

    function showGameController() {
        $('.bet-controller').hide();
        $('.game-controller').show();
    }

    function showBetController() {
        $('.game-controller').hide();
        $('.bet-controller').show();
    }
});