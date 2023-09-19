import 'package:woojoo/data/memory/game/dto_game.dart';

abstract interface class GameRepository {
  Future<List<Game>> getMyGameList();
  Future<List<Game>> updateMyGameList(List<String> gameList);
}