package tw.y_studio.ptt.di

import org.koin.dsl.module
import tw.y_studio.ptt.source.remote.board.BoardRemoteDataSourceImpl
import tw.y_studio.ptt.source.remote.board.IBoardRemoteDataSource
import tw.y_studio.ptt.source.remote.post.IPostRemoteDataSource
import tw.y_studio.ptt.source.remote.post.PostRemoteDataSourceImpl
import tw.y_studio.ptt.source.remote.search.ISearchBoardRemoteDataSource
import tw.y_studio.ptt.source.remote.search.SearchBoardRemoteDataSourceImpl

val dataSourceModules = module {
    factory<IBoardRemoteDataSource> { BoardRemoteDataSourceImpl(get()) }
    factory<ISearchBoardRemoteDataSource> { SearchBoardRemoteDataSourceImpl(get()) }
    factory<IPostRemoteDataSource> { PostRemoteDataSourceImpl(get()) }
}
