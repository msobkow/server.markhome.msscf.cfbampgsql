// Description: Java 11 PostgreSQL Jdbc DbIO implementation for Relation.

/*
 *	server.markhome.msscf.CFBam
 *
 *	Copyright (c) 2020-2025 Mark Stephen Sobkow
 *	
 *
 *	Manufactured by MSS Code Factory 2.13
 */

package server.markhome.msscf.cfbam.CFBamPgSql;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.msscf.msscf.cflib.CFLib.*;
import server.markhome.msscf.cfsec.CFSec.*;
import server.markhome.msscf.cfint.CFInt.*;
import server.markhome.msscf.cfbam.CFBam.*;
import server.markhome.msscf.cfsec.CFSecObj.*;
import server.markhome.msscf.cfint.CFIntObj.*;
import server.markhome.msscf.cfbam.CFBamObj.*;

/*
 *	CFBamPgSqlRelationTable PostgreSQL Jdbc DbIO implementation
 *	for Relation.
 */
public class CFBamPgSqlRelationTable
	implements ICFBamRelationTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByTenantIdx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtReadBuffByRelnTenantIdx = null;
	protected PreparedStatement stmtReadBuffByRelTableIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByFromKeyIdx = null;
	protected PreparedStatement stmtReadBuffByToTblIdx = null;
	protected PreparedStatement stmtReadBuffByToKeyIdx = null;
	protected PreparedStatement stmtReadBuffByNarrowedIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;
	protected PreparedStatement stmtDeleteByRelnTenantIdx = null;
	protected PreparedStatement stmtDeleteByRelTableIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByFromKeyIdx = null;
	protected PreparedStatement stmtDeleteByToTblIdx = null;
	protected PreparedStatement stmtDeleteByToKeyIdx = null;
	protected PreparedStatement stmtDeleteByNarrowedIdx = null;

	public CFBamPgSqlRelationTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createRelation( CFSecAuthorization Authorization,
		CFBamRelationBuff Buff )
	{
		final String S_ProcName = "createRelation";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long TableId = Buff.getRequiredTableId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			String Name = Buff.getRequiredName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			ICFBamSchema.RelationTypeEnum RelationType = Buff.getRequiredRelationType();
			String DbName = Buff.getOptionalDbName();
			String Suffix = Buff.getOptionalSuffix();
			long FromIndexId = Buff.getRequiredFromIndexId();
			long ToTableId = Buff.getRequiredToTableId();
			long ToIndexId = Buff.getRequiredToIndexId();
			boolean IsRequired = Buff.getRequiredIsRequired();
			boolean IsXsdContainer = Buff.getRequiredIsXsdContainer();
			boolean IsLateResolver = Buff.getRequiredIsLateResolver();
			boolean AllowAddendum = Buff.getRequiredAllowAddendum();
			Long NarrowedTenantId = Buff.getOptionalNarrowedTenantId();
			Long NarrowedId = Buff.getOptionalNarrowedId();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_reldef( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtCreateByPKey.setString( argIdx++, ClassCode );
			stmtCreateByPKey.setLong( argIdx++, TenantId );
			stmtCreateByPKey.setLong( argIdx++, TableId );
			if( DefSchemaTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtCreateByPKey.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtCreateByPKey.setString( argIdx++, Name );
			if( ShortName != null ) {
				stmtCreateByPKey.setString( argIdx++, ShortName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Label != null ) {
				stmtCreateByPKey.setString( argIdx++, Label );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortDescription != null ) {
				stmtCreateByPKey.setString( argIdx++, ShortDescription );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Description != null ) {
				stmtCreateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtCreateByPKey.setShort( argIdx++, (short)RelationType.ordinal() );
			if( DbName != null ) {
				stmtCreateByPKey.setString( argIdx++, DbName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Suffix != null ) {
				stmtCreateByPKey.setString( argIdx++, Suffix );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtCreateByPKey.setLong( argIdx++, FromIndexId );
			stmtCreateByPKey.setLong( argIdx++, ToTableId );
			stmtCreateByPKey.setLong( argIdx++, ToIndexId );
			stmtCreateByPKey.setBoolean( argIdx++, IsRequired );
			stmtCreateByPKey.setBoolean( argIdx++, IsXsdContainer );
			stmtCreateByPKey.setBoolean( argIdx++, IsLateResolver );
			stmtCreateByPKey.setBoolean( argIdx++, AllowAddendum );
			if( NarrowedTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, NarrowedTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NarrowedId != null ) {
				stmtCreateByPKey.setLong( argIdx++, NarrowedId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamRelationBuff createdBuff = unpackRelationResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTenantId( createdBuff.getRequiredTenantId() );
				Buff.setRequiredId( createdBuff.getRequiredId() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
				Buff.setRequiredTableId( createdBuff.getRequiredTableId() );
				Buff.setOptionalDefSchemaTenantId( createdBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( createdBuff.getOptionalDefSchemaId() );
				Buff.setRequiredName( createdBuff.getRequiredName() );
				Buff.setOptionalShortName( createdBuff.getOptionalShortName() );
				Buff.setOptionalLabel( createdBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( createdBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( createdBuff.getOptionalDescription() );
				Buff.setRequiredRelationType( createdBuff.getRequiredRelationType() );
				Buff.setOptionalDbName( createdBuff.getOptionalDbName() );
				Buff.setOptionalSuffix( createdBuff.getOptionalSuffix() );
				Buff.setRequiredFromIndexId( createdBuff.getRequiredFromIndexId() );
				Buff.setRequiredToTableId( createdBuff.getRequiredToTableId() );
				Buff.setRequiredToIndexId( createdBuff.getRequiredToIndexId() );
				Buff.setRequiredIsRequired( createdBuff.getRequiredIsRequired() );
				Buff.setRequiredIsXsdContainer( createdBuff.getRequiredIsXsdContainer() );
				Buff.setRequiredIsLateResolver( createdBuff.getRequiredIsLateResolver() );
				Buff.setRequiredAllowAddendum( createdBuff.getRequiredAllowAddendum() );
				Buff.setOptionalNarrowedTenantId( createdBuff.getOptionalNarrowedTenantId() );
				Buff.setOptionalNarrowedId( createdBuff.getOptionalNarrowedId() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public String S_sqlSelectRelationDistinctClassCode = null;

	public String getSqlSelectRelationDistinctClassCode() {
		if( S_sqlSelectRelationDistinctClassCode == null ) {
			S_sqlSelectRelationDistinctClassCode =
					"SELECT "
					+		"DISTINCT a801.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".reldef a835 ON "
					+		"a835.TenantId = a801.TenantId "
					+		"AND a835.Id = a801.Id ";
		}
		return( S_sqlSelectRelationDistinctClassCode );
	}

	public String S_sqlSelectRelationBuff = null;

	public String getSqlSelectRelationBuff() {
		if( S_sqlSelectRelationBuff == null ) {
			S_sqlSelectRelationBuff =
					"SELECT "
					+		"a801.ClassCode, "
					+		"a801.TenantId, "
					+		"a801.Id, "
					+		"a835.TableId, "
					+		"a835.defschtentid, "
					+		"a835.defschid, "
					+		"a835.safe_name, "
					+		"a835.short_name, "
					+		"a835.Label, "
					+		"a835.short_descr, "
					+		"a835.descr, "
					+		"a835.RelationType, "
					+		"a835.DbName, "
					+		"a835.Suffix, "
					+		"a835.FromIndexId, "
					+		"a835.ToTableId, "
					+		"a835.ToIndexId, "
					+		"a835.IsRequired, "
					+		"a835.IsXsdContainer, "
					+		"a835.IsLateResolver, "
					+		"a835.AllowAddendum, "
					+		"a835.NarrowedTenantId, "
					+		"a835.NarrowedId, "
					+		"a801.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".scopedef AS a801 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".reldef a835 ON "
					+		"a835.TenantId = a801.TenantId "
					+		"AND a835.Id = a801.Id ";
		}
		return( S_sqlSelectRelationBuff );
	}

	protected CFBamRelationBuff unpackRelationResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackRelationResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamRelationBuff buff;
		if( classCode.equals( "a835" ) ) {
			buff = schema.getFactoryRelation().newBuff();
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Unrecognized class code \"" + classCode + "\"" );
		}
		{
			String colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedByUserId( null );
			}
			else {
				buff.setCreatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedAt( null );
			}
			else {
				buff.setCreatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedByUserId( null );
			}
			else {
				buff.setUpdatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedAt( null );
			}
			else {
				buff.setUpdatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
		}
		buff.setRequiredTenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredTableId( resultSet.getLong( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefSchemaTenantId( null );
			}
			else {
				buff.setOptionalDefSchemaTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefSchemaId( null );
			}
			else {
				buff.setOptionalDefSchemaId( colVal );
			}
		}
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalShortName( null );
			}
			else {
				buff.setOptionalShortName( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalLabel( null );
			}
			else {
				buff.setOptionalLabel( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalShortDescription( null );
			}
			else {
				buff.setOptionalShortDescription( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDescription( null );
			}
			else {
				buff.setOptionalDescription( colVal );
			}
		}
		idxcol++;
		buff.setRequiredRelationType( CFBamSchema.ordinalToRelationTypeEnum( resultSet.getShort( idxcol ) ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDbName( null );
			}
			else {
				buff.setOptionalDbName( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalSuffix( null );
			}
			else {
				buff.setOptionalSuffix( colVal );
			}
		}
		idxcol++;
		buff.setRequiredFromIndexId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredToTableId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredToIndexId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredIsRequired( resultSet.getBoolean( idxcol ) );
		idxcol++;
		buff.setRequiredIsXsdContainer( resultSet.getBoolean( idxcol ) );
		idxcol++;
		buff.setRequiredIsLateResolver( resultSet.getBoolean( idxcol ) );
		idxcol++;
		buff.setRequiredAllowAddendum( resultSet.getBoolean( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalNarrowedTenantId( null );
			}
			else {
				buff.setOptionalNarrowedTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalNarrowedId( null );
			}
			else {
				buff.setOptionalNarrowedId( colVal );
			}
		}
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamRelationBuff readDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamRelationBuff lockDerived( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFBamRelationBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamRelationBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFBamRelationBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlRelationTable.readDerivedByIdIdx() ";
		CFBamRelationBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		return( buff );
	}

	public CFBamRelationBuff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamRelationBuff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlRelationTable.readDerivedByUNameIdx() ";
		CFBamRelationBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				TenantId,
				TableId,
				Name );
		return( buff );
	}

	public CFBamRelationBuff[] readDerivedByRelnTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByRelnTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByRelnTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFBamRelationBuff[] readDerivedByRelTableIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId )
	{
		final String S_ProcName = "readDerivedByRelTableIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByRelTableIdx( Authorization,
				TenantId,
				TableId );
		return( buffList );

	}

	public CFBamRelationBuff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByDefSchemaIdx( Authorization,
				DefSchemaTenantId,
				DefSchemaId );
		return( buffList );

	}

	public CFBamRelationBuff[] readDerivedByFromKeyIdx( CFSecAuthorization Authorization,
		long TenantId,
		long FromIndexId )
	{
		final String S_ProcName = "readDerivedByFromKeyIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByFromKeyIdx( Authorization,
				TenantId,
				FromIndexId );
		return( buffList );

	}

	public CFBamRelationBuff[] readDerivedByToTblIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ToTableId )
	{
		final String S_ProcName = "readDerivedByToTblIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByToTblIdx( Authorization,
				TenantId,
				ToTableId );
		return( buffList );

	}

	public CFBamRelationBuff[] readDerivedByToKeyIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ToIndexId )
	{
		final String S_ProcName = "readDerivedByToKeyIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByToKeyIdx( Authorization,
				TenantId,
				ToIndexId );
		return( buffList );

	}

	public CFBamRelationBuff[] readDerivedByNarrowedIdx( CFSecAuthorization Authorization,
		Long NarrowedTenantId,
		Long NarrowedId )
	{
		final String S_ProcName = "readDerivedByNarrowedIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamRelationBuff[] buffList = readBuffByNarrowedIdx( Authorization,
				NarrowedTenantId,
				NarrowedId );
		return( buffList );

	}

	public CFBamRelationBuff readBuff( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "readBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPKey == null ) {
				stmtReadBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPKey.setLong( argIdx++, TenantId );
			stmtReadBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff lockBuff( CFSecAuthorization Authorization,
		CFBamScopePKey PKey )
	{
		final String S_ProcName = "lockBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_reldef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtLockBuffByPKey == null ) {
				stmtLockBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockBuffByPKey.setLong( argIdx++, TenantId );
			stmtLockBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllBuff == null ) {
				stmtReadAllBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllBuff.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIdIdx == null ) {
				stmtReadBuffByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByTenantIdx == null ) {
				stmtReadBuffByTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByTenantIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUNameIdx == null ) {
				stmtReadBuffByUNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByUNameIdx.setLong( argIdx++, TableId );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByRelnTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByRelnTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_relntenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByRelnTenantIdx == null ) {
				stmtReadBuffByRelnTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByRelnTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRelnTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByRelnTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByRelnTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRelnTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByRelnTenantIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByRelnTenantIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByRelTableIdx( CFSecAuthorization Authorization,
		long TenantId,
		long TableId )
	{
		final String S_ProcName = "readBuffByRelTableIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_reltableidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByRelTableIdx == null ) {
				stmtReadBuffByRelTableIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByRelTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRelTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByRelTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByRelTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByRelTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByRelTableIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByRelTableIdx.setLong( argIdx++, TableId );
			resultSet = stmtReadBuffByRelTableIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByDefSchemaIdx == null ) {
				stmtReadBuffByDefSchemaIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( DefSchemaTenantId != null ) {
				stmtReadBuffByDefSchemaIdx.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtReadBuffByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtReadBuffByDefSchemaIdx.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtReadBuffByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByDefSchemaIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByFromKeyIdx( CFSecAuthorization Authorization,
		long TenantId,
		long FromIndexId )
	{
		final String S_ProcName = "readBuffByFromKeyIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_fromkeyidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByFromKeyIdx == null ) {
				stmtReadBuffByFromKeyIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByFromKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByFromKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByFromKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByFromKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByFromKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByFromKeyIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByFromKeyIdx.setLong( argIdx++, FromIndexId );
			resultSet = stmtReadBuffByFromKeyIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByToTblIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ToTableId )
	{
		final String S_ProcName = "readBuffByToTblIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_totblidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByToTblIdx == null ) {
				stmtReadBuffByToTblIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByToTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByToTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByToTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByToTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByToTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByToTblIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByToTblIdx.setLong( argIdx++, ToTableId );
			resultSet = stmtReadBuffByToTblIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByToKeyIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ToIndexId )
	{
		final String S_ProcName = "readBuffByToKeyIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_tokeyidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByToKeyIdx == null ) {
				stmtReadBuffByToKeyIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByToKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByToKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByToKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByToKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByToKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByToKeyIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByToKeyIdx.setLong( argIdx++, ToIndexId );
			resultSet = stmtReadBuffByToKeyIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamRelationBuff[] readBuffByNarrowedIdx( CFSecAuthorization Authorization,
		Long NarrowedTenantId,
		Long NarrowedId )
	{
		final String S_ProcName = "readBuffByNarrowedIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_reldef_by_narrowedidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByNarrowedIdx == null ) {
				stmtReadBuffByNarrowedIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByNarrowedIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNarrowedIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByNarrowedIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByNarrowedIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNarrowedIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( NarrowedTenantId != null ) {
				stmtReadBuffByNarrowedIdx.setLong( argIdx++, NarrowedTenantId.longValue() );
			}
			else {
				stmtReadBuffByNarrowedIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NarrowedId != null ) {
				stmtReadBuffByNarrowedIdx.setLong( argIdx++, NarrowedId.longValue() );
			}
			else {
				stmtReadBuffByNarrowedIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByNarrowedIdx.executeQuery();
			List<CFBamRelationBuff> buffList = new LinkedList<CFBamRelationBuff>();
			while( resultSet.next() ) {
				CFBamRelationBuff buff = unpackRelationResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamRelationBuff[] retBuff = new CFBamRelationBuff[ buffList.size() ];
			Iterator<CFBamRelationBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void updateRelation( CFSecAuthorization Authorization,
		CFBamRelationBuff Buff )
	{
		final String S_ProcName = "updateRelation";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long TableId = Buff.getRequiredTableId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			String Name = Buff.getRequiredName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			ICFBamSchema.RelationTypeEnum RelationType = Buff.getRequiredRelationType();
			String DbName = Buff.getOptionalDbName();
			String Suffix = Buff.getOptionalSuffix();
			long FromIndexId = Buff.getRequiredFromIndexId();
			long ToTableId = Buff.getRequiredToTableId();
			long ToIndexId = Buff.getRequiredToIndexId();
			boolean IsRequired = Buff.getRequiredIsRequired();
			boolean IsXsdContainer = Buff.getRequiredIsXsdContainer();
			boolean IsLateResolver = Buff.getRequiredIsLateResolver();
			boolean AllowAddendum = Buff.getRequiredAllowAddendum();
			Long NarrowedTenantId = Buff.getOptionalNarrowedTenantId();
			Long NarrowedId = Buff.getOptionalNarrowedId();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_reldef( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + " )";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtUpdateByPKey.setString( argIdx++, ClassCode );
			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			stmtUpdateByPKey.setLong( argIdx++, TableId );
			if( DefSchemaTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtUpdateByPKey.setString( argIdx++, Name );
			if( ShortName != null ) {
				stmtUpdateByPKey.setString( argIdx++, ShortName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Label != null ) {
				stmtUpdateByPKey.setString( argIdx++, Label );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortDescription != null ) {
				stmtUpdateByPKey.setString( argIdx++, ShortDescription );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Description != null ) {
				stmtUpdateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setShort( argIdx++, (short)RelationType.ordinal() );
			if( DbName != null ) {
				stmtUpdateByPKey.setString( argIdx++, DbName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Suffix != null ) {
				stmtUpdateByPKey.setString( argIdx++, Suffix );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setLong( argIdx++, FromIndexId );
			stmtUpdateByPKey.setLong( argIdx++, ToTableId );
			stmtUpdateByPKey.setLong( argIdx++, ToIndexId );
			stmtUpdateByPKey.setBoolean( argIdx++, IsRequired );
			stmtUpdateByPKey.setBoolean( argIdx++, IsXsdContainer );
			stmtUpdateByPKey.setBoolean( argIdx++, IsLateResolver );
			stmtUpdateByPKey.setBoolean( argIdx++, AllowAddendum );
			if( NarrowedTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, NarrowedTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NarrowedId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, NarrowedId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamRelationBuff updatedBuff = unpackRelationResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTableId( updatedBuff.getRequiredTableId() );
				Buff.setOptionalDefSchemaTenantId( updatedBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( updatedBuff.getOptionalDefSchemaId() );
				Buff.setRequiredName( updatedBuff.getRequiredName() );
				Buff.setOptionalShortName( updatedBuff.getOptionalShortName() );
				Buff.setOptionalLabel( updatedBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( updatedBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( updatedBuff.getOptionalDescription() );
				Buff.setRequiredRelationType( updatedBuff.getRequiredRelationType() );
				Buff.setOptionalDbName( updatedBuff.getOptionalDbName() );
				Buff.setOptionalSuffix( updatedBuff.getOptionalSuffix() );
				Buff.setRequiredFromIndexId( updatedBuff.getRequiredFromIndexId() );
				Buff.setRequiredToTableId( updatedBuff.getRequiredToTableId() );
				Buff.setRequiredToIndexId( updatedBuff.getRequiredToIndexId() );
				Buff.setRequiredIsRequired( updatedBuff.getRequiredIsRequired() );
				Buff.setRequiredIsXsdContainer( updatedBuff.getRequiredIsXsdContainer() );
				Buff.setRequiredIsLateResolver( updatedBuff.getRequiredIsLateResolver() );
				Buff.setRequiredAllowAddendum( updatedBuff.getRequiredAllowAddendum() );
				Buff.setOptionalNarrowedTenantId( updatedBuff.getOptionalNarrowedTenantId() );
				Buff.setOptionalNarrowedId( updatedBuff.getOptionalNarrowedId() );
				Buff.setRequiredRevision( updatedBuff.getRequiredRevision() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelation( CFSecAuthorization Authorization,
		CFBamRelationBuff Buff )
	{
		final String S_ProcName = "deleteRelation";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
			if( stmtDeleteByPKey == null ) {
				stmtDeleteByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtDeleteByPKey.setLong( argIdx++, TenantId );
			stmtDeleteByPKey.setLong( argIdx++, Id );
			stmtDeleteByPKey.setInt( argIdx++, Buff.getRequiredRevision() );;
			resultSet = stmtDeleteByPKey.executeQuery();
			if( resultSet.next() ) {
				boolean deleteFlag = resultSet.getBoolean( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 record result set to be returned by delete, not 0 rows" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteRelationByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIdIdx == null ) {
					stmtDeleteByIdIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIdIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByIdIdx.setLong( argIdx++, argId );
				resultSet = stmtDeleteByIdIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
			}
			catch( SQLException e ) {
				throw new CFLibDbException( getClass(),
					S_ProcName,
					e );
			}
			finally {
				if( resultSet != null ) {
					try {
						resultSet.close();
					}
					catch( SQLException e ) {
					}
					resultSet = null;
				}
			}
	}

	public void deleteRelationByIdIdx( CFSecAuthorization Authorization,
		CFBamScopePKey argKey )
	{
		deleteRelationByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteRelationByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteRelationByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByTenantIdx == null ) {
					stmtDeleteByTenantIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByTenantIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByTenantIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByTenantIdx( CFSecAuthorization Authorization,
		CFBamScopeByTenantIdxKey argKey )
	{
		deleteRelationByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteRelationByUNameIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argTableId,
		String argName )
	{
		final String S_ProcName = "deleteRelationByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUNameIdx == null ) {
					stmtDeleteByUNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByUNameIdx.setLong( argIdx++, argTableId );
				stmtDeleteByUNameIdx.setString( argIdx++, argName );
				resultSet = stmtDeleteByUNameIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByUNameIdx( CFSecAuthorization Authorization,
		CFBamRelationByUNameIdxKey argKey )
	{
		deleteRelationByUNameIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTableId(),
			argKey.getRequiredName() );
	}

	public void deleteRelationByRelnTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteRelationByRelnTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_relntenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByRelnTenantIdx == null ) {
					stmtDeleteByRelnTenantIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByRelnTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRelnTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByRelnTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByRelnTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRelnTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByRelnTenantIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByRelnTenantIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByRelnTenantIdx( CFSecAuthorization Authorization,
		CFBamRelationByRelnTenantIdxKey argKey )
	{
		deleteRelationByRelnTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteRelationByRelTableIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argTableId )
	{
		final String S_ProcName = "deleteRelationByRelTableIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_reltableidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByRelTableIdx == null ) {
					stmtDeleteByRelTableIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByRelTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRelTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByRelTableIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByRelTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByRelTableIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByRelTableIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByRelTableIdx.setLong( argIdx++, argTableId );
				resultSet = stmtDeleteByRelTableIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByRelTableIdx( CFSecAuthorization Authorization,
		CFBamRelationByRelTableIdxKey argKey )
	{
		deleteRelationByRelTableIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredTableId() );
	}

	public void deleteRelationByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deleteRelationByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByDefSchemaIdx == null ) {
					stmtDeleteByDefSchemaIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argDefSchemaTenantId != null ) {
					stmtDeleteByDefSchemaIdx.setLong( argIdx++, argDefSchemaTenantId.longValue() );
				}
				else {
					stmtDeleteByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argDefSchemaId != null ) {
					stmtDeleteByDefSchemaIdx.setLong( argIdx++, argDefSchemaId.longValue() );
				}
				else {
					stmtDeleteByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByDefSchemaIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamRelationByDefSchemaIdxKey argKey )
	{
		deleteRelationByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deleteRelationByFromKeyIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argFromIndexId )
	{
		final String S_ProcName = "deleteRelationByFromKeyIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_fromkeyidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByFromKeyIdx == null ) {
					stmtDeleteByFromKeyIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByFromKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByFromKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByFromKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByFromKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByFromKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByFromKeyIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByFromKeyIdx.setLong( argIdx++, argFromIndexId );
				resultSet = stmtDeleteByFromKeyIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByFromKeyIdx( CFSecAuthorization Authorization,
		CFBamRelationByFromKeyIdxKey argKey )
	{
		deleteRelationByFromKeyIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredFromIndexId() );
	}

	public void deleteRelationByToTblIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argToTableId )
	{
		final String S_ProcName = "deleteRelationByToTblIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_totblidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByToTblIdx == null ) {
					stmtDeleteByToTblIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByToTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByToTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByToTblIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByToTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByToTblIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByToTblIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByToTblIdx.setLong( argIdx++, argToTableId );
				resultSet = stmtDeleteByToTblIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByToTblIdx( CFSecAuthorization Authorization,
		CFBamRelationByToTblIdxKey argKey )
	{
		deleteRelationByToTblIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredToTableId() );
	}

	public void deleteRelationByToKeyIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argToIndexId )
	{
		final String S_ProcName = "deleteRelationByToKeyIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_tokeyidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByToKeyIdx == null ) {
					stmtDeleteByToKeyIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByToKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByToKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByToKeyIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByToKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByToKeyIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByToKeyIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByToKeyIdx.setLong( argIdx++, argToIndexId );
				resultSet = stmtDeleteByToKeyIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByToKeyIdx( CFSecAuthorization Authorization,
		CFBamRelationByToKeyIdxKey argKey )
	{
		deleteRelationByToKeyIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredToIndexId() );
	}

	public void deleteRelationByNarrowedIdx( CFSecAuthorization Authorization,
		Long argNarrowedTenantId,
		Long argNarrowedId )
	{
		final String S_ProcName = "deleteRelationByNarrowedIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_reldef_by_narrowedidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByNarrowedIdx == null ) {
					stmtDeleteByNarrowedIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByNarrowedIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNarrowedIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByNarrowedIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByNarrowedIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNarrowedIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argNarrowedTenantId != null ) {
					stmtDeleteByNarrowedIdx.setLong( argIdx++, argNarrowedTenantId.longValue() );
				}
				else {
					stmtDeleteByNarrowedIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argNarrowedId != null ) {
					stmtDeleteByNarrowedIdx.setLong( argIdx++, argNarrowedId.longValue() );
				}
				else {
					stmtDeleteByNarrowedIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByNarrowedIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteRelationByNarrowedIdx( CFSecAuthorization Authorization,
		CFBamRelationByNarrowedIdxKey argKey )
	{
		deleteRelationByNarrowedIdx( Authorization,
			argKey.getOptionalNarrowedTenantId(),
			argKey.getOptionalNarrowedId() );
	}

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectRelationDistinctClassCode = null;
		S_sqlSelectRelationBuff = null;

		if( stmtReadBuffByPKey != null ) {
			try {
				stmtReadBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPKey = null;
		}
		if( stmtReadBuffAll != null ) {
			try {
				stmtReadBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffAll = null;
		}
		if( stmtLockBuffByPKey != null ) {
			try {
				stmtLockBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtLockBuffByPKey = null;
		}
		if( stmtCreateByPKey != null ) {
			try {
				stmtCreateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtCreateByPKey = null;
		}
		if( stmtUpdateByPKey != null ) {
			try {
				stmtUpdateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtUpdateByPKey = null;
		}
		if( stmtDeleteByPKey != null ) {
			try {
				stmtDeleteByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPKey = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByTenantIdx != null ) {
			try {
				stmtDeleteByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByTenantIdx = null;
		}
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
		}
		if( stmtDeleteByRelnTenantIdx != null ) {
			try {
				stmtDeleteByRelnTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByRelnTenantIdx = null;
		}
		if( stmtDeleteByRelTableIdx != null ) {
			try {
				stmtDeleteByRelTableIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByRelTableIdx = null;
		}
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByFromKeyIdx != null ) {
			try {
				stmtDeleteByFromKeyIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByFromKeyIdx = null;
		}
		if( stmtDeleteByToTblIdx != null ) {
			try {
				stmtDeleteByToTblIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByToTblIdx = null;
		}
		if( stmtDeleteByToKeyIdx != null ) {
			try {
				stmtDeleteByToKeyIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByToKeyIdx = null;
		}
		if( stmtDeleteByNarrowedIdx != null ) {
			try {
				stmtDeleteByNarrowedIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByNarrowedIdx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadBuffByTenantIdx != null ) {
			try {
				stmtReadBuffByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByTenantIdx = null;
		}
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
		if( stmtReadBuffByRelnTenantIdx != null ) {
			try {
				stmtReadBuffByRelnTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByRelnTenantIdx = null;
		}
		if( stmtReadBuffByRelTableIdx != null ) {
			try {
				stmtReadBuffByRelTableIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByRelTableIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadBuffByFromKeyIdx != null ) {
			try {
				stmtReadBuffByFromKeyIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByFromKeyIdx = null;
		}
		if( stmtReadBuffByToTblIdx != null ) {
			try {
				stmtReadBuffByToTblIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByToTblIdx = null;
		}
		if( stmtReadBuffByToKeyIdx != null ) {
			try {
				stmtReadBuffByToKeyIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByToKeyIdx = null;
		}
		if( stmtReadBuffByNarrowedIdx != null ) {
			try {
				stmtReadBuffByNarrowedIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByNarrowedIdx = null;
		}
	}
}
